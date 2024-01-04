#include "protected.h"

// Pour chaque fonction, la logique est la meme que pour CellKey puisque l'on
// continue de manipuler de linked lists
typedef struct CellProtected
{
    Protected *data;
    struct CellProtected *next;
} CellProtected;

CellProtected *create_cell_protected(Protected *pr)
{
    CellProtected *cellProtected = malloc(sizeof(CellProtected));
    cellProtected->data = init_protected(pr->pKey, pr->mess, pr->sgn);
    cellProtected->next = NULL;
    return cellProtected;
}

CellProtected *add_protected(CellProtected *head, Protected *newPr)
{
    CellProtected *newNode = create_cell_protected(newPr);
    CellProtected *tmp = head;

    if (tmp == NULL)
    {
        return newNode;
    }

    while (tmp->next != NULL)
    {
        tmp = tmp->next;
    }

    CellProtected **ptr = &tmp;
    (*ptr)->next = newNode;
    return head;
}

CellProtected *read_list_protected(char *filePath)
{
    FILE *fKeyptr;
    fKeyptr = fopen(filePath, "r");

    CellProtected *head = NULL;
    Protected *pr;

    int MAX_LINE_LENGTH = 80;
    char line[MAX_LINE_LENGTH];
    while (fgets(line, MAX_LINE_LENGTH, fKeyptr))
    {
        pr = str_to_protected(line);
        head = add_protected(head, pr);
    }
    return head;
}

void print_list_protected(CellProtected *protectedList)
{
    CellProtected *ptr = protectedList;

    while (ptr != NULL)
    {
        printf("%s \n", protected_to_str(ptr->data));
        ptr = ptr->next;
    }
}

void delete_list_protected(CellProtected **pr)
{
    CellProtected *current = *pr;
    CellProtected *next;

    if (pr != NULL)
    {
        next = current->next;
        free(current);
        current = next;
    }

    *pr = current;
}