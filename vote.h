#include <dirent.h>

void submit_vote(Protected *p)
{
    FILE *fKeyptr;
    fKeyptr = fopen("./Pending_votes.txt", "a");
    fprintf(fKeyptr, "%s\n", protected_to_str(p));
    fclose(fKeyptr);
    return;
}

void create_block(CellTree *tree, Key *author, int d)
{
    Block *block = malloc(sizeof(Block));
    CellProtected *pr = read_list_protected("Pending_votes.txt");
    remove("Pending_votes.txt");
    block->author = author;
    block->previous_hash = last_node(tree)->block->hash;
    block->votes = pr;
    block->nonce = 0;

    compute_proof_of_work(block, d);
    block->hash = fake_sha(block_to_str(block));

    CellTree *child = create_node(block);
    add_child(tree, child);

    write_block(block, "Pending_block.txt");
}

void add_block(int d, char *name)
{
    Block *block = read_block("Pending_block.txt");
    remove("Pending_block.txt");
    if (verify_block(block, d) == 1)
    {
        char filePath[1000];
        sprintf(filePath, "./Blockchain/%s.txt", name);
        remove(filePath);
        write_block(block, filePath);
    };
    return;
}

CellTree *read_tree()
{
    CellTree **treeTable = malloc(sizeof(CellTree) * 10000);
    int i = 0;
    DIR *d = opendir("./Blockchain");
    if (d != NULL)
    {
        struct dirent *dir;
        while ((dir = readdir(d)))
        {
            if ((strcmp(dir->d_name, ".") != 0 && strcmp(dir->d_name, "..") != 0))
            {
                char filePath[100];
                sprintf(filePath, "Blockchain/%s", dir->d_name);
                treeTable[i] = create_node(read_block(filePath));
                i++;
            }
        }
        closedir(d);
    }

    for (int j = 0; j < i; j++)
    {

        // On fixe la cellule que l'on veut associer
        if (treeTable[j] != NULL)
        {
            // On regarde toutes les autres cellules et on associe si le previous_hash est egal au hash de
            //  la cellule que l'on considere
            for (int k = 0; k < i; k++)
            {
                if (treeTable[j]->block->hash == treeTable[k]->block->previous_hash)
                {
                    add_child(treeTable[j], treeTable[k]);
                }
            }
        }
    }

    for (int p = 0; p < i; p++)
    {
        if (treeTable[p]->father == NULL)
        {
            return treeTable[p];
        }
    }

    return NULL;
}

Key *compute_winner_BT(CellTree *tree, CellKey *candidates, CellKey *voters, int sizeC, int sizeV)
{
    CellProtected *declarations = longest_chain(tree);
    delete_invalid_protected(declarations);
    return compute_winner(declarations, candidates, voters, sizeC, sizeV);
}