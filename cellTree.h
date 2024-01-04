#include "block.h"

typedef struct CellTree
{
    Block *block;
    struct CellTree *father;
    struct CellTree *firstChild;
    struct CellTree *nextBro;
    int height;
} CellTree;

CellTree *create_node(Block *b)
{
    CellTree *cellTree = malloc(sizeof(CellTree));
    cellTree->block = b;
    cellTree->father = NULL;
    cellTree->firstChild = NULL;
    cellTree->nextBro = NULL;
    cellTree->height = 0;

    return cellTree;
}

int update_height(CellTree *father, CellTree *child)
{
    int newHeight = father->height >= (child->height + 1) ? father->height : child->height + 1;
    int hasChanged = newHeight == father->height;

    CellTree **ptr = &father;
    (*ptr)->height = newHeight;
    return hasChanged;
}

void add_child(CellTree *father, CellTree *child)
{
    CellTree *tmp = father;

    // Si la cellule n'a pas de fils, on peut l'ajouter directement

    if (father->firstChild == NULL)
    {
        father->firstChild = child;
        father->firstChild->father = father;
    }
    else
    {
        // Sinon, on cherche le dernier frere et on ajoute child a la liste de freres
        while (tmp->nextBro != NULL)
        {
            tmp = tmp->nextBro;
        }

        CellTree **ptr = &tmp;
        (*ptr)->nextBro = child;
        (*ptr)->nextBro->father = father;
    }

    CellTree *dad = father;
    CellTree *newChild = child;

    while (dad->father != NULL)
    {
        update_height(dad->father, newChild);
        newChild = dad;
        dad = dad->father;
    }
}

void print_tree(CellTree *father)
{
    CellTree *tmp = father;

    while (tmp != NULL)
    {
        printf("%i %u\n", tmp->height, tmp->block->hash);
        while (tmp->nextBro != NULL)
        {
            printf("%i %u\n", tmp->nextBro->height, tmp->nextBro->block->hash);
            tmp->nextBro = tmp->nextBro->nextBro;
            print_tree(tmp->nextBro->firstChild);
        }

        if (tmp->firstChild != NULL)
        {
            printf("\n%i %u\n", tmp->firstChild->height, tmp->firstChild->block->hash);
            print_tree(tmp->firstChild->firstChild);
        }
        tmp = tmp->firstChild;
    }
    return;
}

CellTree *highest_child(CellTree *cell)
{
    CellTree *tmp = cell;
    CellTree *highestChild = cell->firstChild;

    // Si firstChild est nul, la cellule n'a pas de fils et donc il n'y a rien a retourne
    if (highestChild == NULL)
    {
        return NULL;
    }

    // Sinon, on parcourt la liste des freres et on cherche celui dont la hauteur est la plus elevee
    while (tmp != NULL && tmp->nextBro != NULL)
    {
        if (tmp->nextBro->height > highestChild->height)
        {
            highestChild = tmp;
            tmp = tmp->nextBro;
        }
        tmp = tmp->nextBro;
    }

    return highestChild;
}

CellTree *last_node(CellTree *tree)
{
    CellTree *tmp = tree;
    CellTree *highestChild = tmp;

    // On cherche le fils avec la plus grande hauteur parmi les fils de tree, puis on
    // repete l'operation sur les fils de cette cellule, etc.
    while (tmp->firstChild != NULL)
    {
        highestChild = highest_child(tmp);
        tmp = highestChild;
    }

    return highestChild;
}

CellProtected *merge_lists(CellProtected *p1, CellProtected *p2)
{
    CellProtected *tmp = p1;

    if (tmp == NULL)
    {
        return p2;
    }

    // On cherche la fin de p1 et on ajoute p2 a la fin de la liste
    // Complexite de O(longueur de p1)

    while (tmp->next != NULL)
    {
        tmp = tmp->next;
    }

    CellProtected **ptr = &tmp;

    (*ptr)->next = p2;
    return p1;
}

CellProtected *longest_chain(CellTree *father)
{

    CellTree *tmp = father;
    CellProtected *cp = father->block->votes;
    CellProtected *tmpPr = cp;

    while (tmp->firstChild != NULL)
    {
        // On cherche le fils avec la plus grande hauteur
        CellTree *highestChild = highest_child(tmp);

        // On ajoute la liste de cellules protected a la fin de l'actuelle
        while (tmpPr->next != NULL)
        {
            tmpPr = tmpPr->next;
        }

        CellProtected **ptr = &tmpPr;

        (*ptr)->next = highestChild->block->votes;
        tmp = highestChild;
    }

    return cp;
}