#include <time.h>
#include "basicFunctions.h"
#include "helpers.h"
#include "rsa.h"
#include "cellKey.h"
#include "signature.h"
#include "cellProtected.h"
#include "hash.h"
#include "declaration.h"
#include "cellTree.h"
#include "vote.h"

int main()
{
    srand(10);

    // Testing Init Keys
    Key *pKey = malloc(sizeof(Key));
    Key *sKey = malloc(sizeof(Key));

    init_pair_keys(pKey, sKey, 5, 7);

    printf("pKey : %ld, %ld \n", pKey->val, pKey->n);
    printf("sKey : %ld, %ld \n", sKey->val, sKey->n);

    printf("pKey : %lx, %lx \n", pKey->val, pKey->n);
    printf("sKey : %lx, %lx \n", sKey->val, sKey->n);

    printf("\n%i\n", modpow(modpow(40, sKey->val, sKey->n), pKey->val, pKey->n));

    // Testing Key Serialization

    char *chaine = key_to_str(pKey);
    printf("key to str : %s \n", chaine);
    Key *k = str_to_key(chaine);
    printf("str to key : %lx, %lx \n", k->val, k->n);

    // // Testing signature
    // // Candidate keys:
    Key *pKeyC = malloc(sizeof(Key));
    Key *sKeyC = malloc(sizeof(Key));

    init_pair_keys(pKeyC, sKeyC, 3, 7);

    // // Declaration:
    char *mess = key_to_str(pKeyC);
    printf("%s vote pour %s \n", key_to_str(pKey), mess);
    printf("sKey est %s \n", key_to_str(sKey));
    Signature *sgn = sign(mess, sKey);
    printf("signature : ");
    print_long_vector(sgn->content, sgn->size);

    chaine = signature_to_str(sgn);
    printf("signature to str: %s \n", chaine);
    sgn = str_to_signature(chaine);
    printf("str to signature : ");
    print_long_vector(sgn->content, sgn->size);

    // Testing protected:
    Protected *pr = init_protected(pKey, mess, sgn);
    // Verification:
    if (verify(pr))
    {
        printf("Signature valide \n");
    }
    else
    {
        printf("Signature non valide \n");
    }
    chaine = protected_to_str(pr);
    printf("protected to str : %s \n", chaine);
    pr = str_to_protected(chaine);
    printf("str to protected: %s %s %s \n", key_to_str(pr->pKey), pr->mess, signature_to_str(pr->sgn));

    int nv = 1000;
    int nc = 10;
    generate_random_data(nv, nc);

    CellKey *voters = read_public_keys("./keys.txt");
    CellKey *candidates = read_public_keys("./candidates.txt");
    // print_list_keys(pCKeys);
    //  printf("####\n");
    //  print_list_keys(sCKeys);

    CellProtected *prKeys = read_list_protected("./declarations.txt");
    // printf("####\n");
    // print_list_protected(prKeys);

    DIR *rep = opendir("./Blockchain");
    if (rep != NULL)
    {
        struct dirent *dir;
        while ((dir = readdir(rep)))
        {
            if ((strcmp(dir->d_name, ".") != 0 && strcmp(dir->d_name, "..") != 0))
            {
                char filePath[100];
                sprintf(filePath, "Blockchain/%s", dir->d_name);
                remove(filePath);
            }
        }
        closedir(rep);
    }

    int voteCount = 0;
    int d = 2;
    int voteBlock = 20;
    char name[1000];
    CellTree *tree = create_node(nullBlock());
    Key *author = voters->data;
    while (prKeys != NULL)
    {
        submit_vote(prKeys->data);
        prKeys = prKeys->next;

        voteCount++;

        if (voteCount % voteBlock == 0)
        {
            create_block(tree, author, d);
            sprintf(name, "block#%i", voteCount / voteBlock + 1);
            add_block(d, name);
            tree = tree->firstChild;
        }
    }

    CellTree *voteTree = read_tree();

    Key *winner = compute_winner_BT(voteTree, candidates, voters, 100 * nc, 100 * nv);
    printf("Le gagnant est %s!\n", key_to_str(winner));

    free(pKey);
    free(sKey);
    free(pKeyC);
    free(sKeyC);
    free(voters);
    free(candidates);
    free(prKeys);
    free(winner);

    return 0;
}
