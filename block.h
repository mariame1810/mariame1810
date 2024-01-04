const int SHA256_DIGEST_LENGTH = 32;

typedef struct Block
{
    Key *author;
    CellProtected *votes;
    unsigned int hash;
    unsigned int previous_hash;
    int nonce;
} Block;

Block *nullBlock()
{

    Block *block = malloc(sizeof(Block));
    block->author = NULL;
    block->votes = NULL;
    block->hash = 0;
    block->previous_hash = 0;
    block->nonce = 0;

    return block;
}

void write_block(Block *block, char *filePath)
{
    FILE *fKeyptr;
    fKeyptr = fopen(filePath, "a");
    fprintf(fKeyptr, "%s\n", key_to_str(block->author));
    fprintf(fKeyptr, "%u\n", block->hash);
    fprintf(fKeyptr, "%u\n", block->previous_hash);
    fprintf(fKeyptr, "%i\n", block->nonce);

    CellProtected *tmp = block->votes;

    while (tmp != NULL)
    {
        fprintf(fKeyptr, "%s\n", protected_to_str(tmp->data));
        tmp = tmp->next;
    }
    fclose(fKeyptr);
}

Block *read_block(char *filePath)
{
    Block *block = malloc(sizeof(Block));
    block->votes = NULL;

    FILE *fKeyptr;
    fKeyptr = fopen(filePath, "r");

    int MAX_LINE_LENGTH = 100;
    char line[MAX_LINE_LENGTH];
    char author[MAX_LINE_LENGTH];
    int nonce;
    fgets(line, MAX_LINE_LENGTH, fKeyptr);
    sscanf(line, "%s\n", author);

    fgets(line, MAX_LINE_LENGTH, fKeyptr);
    sscanf(line, "%u\n", &(block->hash));

    fgets(line, MAX_LINE_LENGTH, fKeyptr);
    sscanf(line, "%u\n", &(block->previous_hash));

    fgets(line, MAX_LINE_LENGTH, fKeyptr);
    sscanf(line, "%i\n", &nonce);

    while (fgets(line, MAX_LINE_LENGTH, fKeyptr))
    {
        block->votes = add_protected(block->votes, str_to_protected(line));
    }

    block->author = str_to_key(author);
    block->nonce = nonce;

    return block;

    fclose(fKeyptr);
}

char *block_to_str(Block *block)
{
    char *s = malloc(sizeof(char) * 100000);
    sprintf(s, "%s%u", key_to_str(block->author), block->previous_hash);

    CellProtected *tmp = block->votes;

    while (tmp != NULL)
    {
        sprintf(s + strlen(s), "%s", protected_to_str(tmp->data));
        tmp = tmp->next;
    }
    sprintf(s + strlen(s), "%i", block->nonce);

    return s;
}

// On remplacera unsigned char * par unsigned int par commodite
unsigned int fake_sha(char *string)
{

    // Ici, le hash est calcule simplement par addition
    // des acsii de chaque character, a default de pouvoir calculer le vrai
    // SHA256

    // A remplacer par SHA256 (s , strlen ( s ) , 0)
    unsigned int sum = 0;

    for (int i = 0; i < strlen(string); i++)
    {
        sum += (string[i] + '0');
    }

    return sum;
}

int starts_with_d_zeros(unsigned int sha, int d)
{

    // Normalement on devrait regarder si les d premiers characteres du sha
    // sont des 0. Il faudrait dans ce cas utiliser unsigned char* au lieu de unsigned int
    return 1;

    // for (int i = 0; i < d; i++)
    // {
    //     char c = sha[i];
    //     if (sha[i] != 0)
    //     {
    //         return 0;
    //     }
    // }
    // return 1;
}

int verify_block(Block *block, int d)
{
    return starts_with_d_zeros(block->hash, d);
}

void compute_proof_of_work(Block *block, int d)
{
    unsigned int sha = fake_sha(block_to_str(block));
    Block **ptr = &block;

    // On incremente nonce jusqu'a obtenir le hash que l'on veut
    while (starts_with_d_zeros(sha, d) == 0)
    {
        (*ptr)->nonce++;

        // A remplacer par
        //  sha = calculate_sha(block_to_str(block));
        break;
    }
}

void delete_block(Block *block)
{
    delete_list_protected(&block->votes);
    free(block);
}