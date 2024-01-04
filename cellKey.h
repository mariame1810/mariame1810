#include "key.h"

typedef struct CellKey
{
    Key *data;
    struct CellKey *next;
} CellKey;

CellKey *create_cell_key(Key *key)
{
    CellKey *cellKey = malloc(sizeof(CellKey));
    cellKey->data = malloc(sizeof(Key));
    init_key(cellKey->data, key->val, key->n);
    cellKey->next = NULL;
    return cellKey;
}

CellKey *add_key(CellKey *head, Key *newKey)
{
    // On cree la nouvelle node que l'on ajoute a la liste
    CellKey *newNode = create_cell_key(newKey);
    // tmp permettra de suivre la liste sans ecraser head
    CellKey *tmp = head;

    if (tmp == NULL)
    {
        // Si head est nulle on peut directement retourner newNode
        return newNode;
    }

    // Sinon on parcourt la liste jusqu'a trouver la fin et on ajoute newNode.
    // L'enonce demande d'ajouter la nouvelle cle a la tete, mais on fait l'inverse ici.
    // Cela permettra d'ajouter les cle dans l'ordre chronologique.
    while (tmp->next != NULL)
    {
        tmp = tmp->next;
    }

    CellKey **ptr = &tmp;
    (*ptr)->next = newNode;
    return head;
}

CellKey *read_public_keys(char *filePath)
{
    FILE *fKeyptr;
    fKeyptr = fopen(filePath, "r");

    CellKey *head = NULL;
    Key *pKey;

    // On parcourt le fichier a filePath et on imprime chaque cle
    int MAX_LINE_LENGTH = 156;
    char pKeyStr[MAX_LINE_LENGTH];
    char sKeyStr[MAX_LINE_LENGTH];
    char line[MAX_LINE_LENGTH];
    while (fgets(line, MAX_LINE_LENGTH, fKeyptr))
    {
        sscanf(line, "%s %s", pKeyStr, sKeyStr);
        pKey = str_to_key(pKeyStr);
        head = add_key(head, pKey);
    }
    return head;
}

void print_list_keys(CellKey *LCK)
{
    CellKey *ptr = LCK;

    while (ptr != NULL)
    {
        printf("%s \n", key_to_str(ptr->data));
        ptr = ptr->next;
    }
}

void delete_cell_key(CellKey **c)
{
    CellKey *current = *c;
    CellKey *next;

    // Pour chaque element de la liste, on preserve next pour pouvoir continuer
    // le deroulement et on dealloc la cle en cours.

    if (c != NULL)
    {
        next = current->next;
        free(current);
        current = next;
    }

    *c = current;
}

void delete_list_keys(CellKey **c)
{
    CellKey *current = *c;
    CellKey *next;

    while (c != NULL)
    {
        next = current->next;
        free(current);
        current = next;
    }

    *c = current;
}