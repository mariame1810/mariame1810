typedef struct Hashcell
{
    Key *key;
    int val;
} HashCell;

typedef struct Hashtable
{
    HashCell **tab;
    int size;
} HashTable;

HashCell *create_hashcell(Key *key)
{
    HashCell *hashCell = malloc(sizeof(HashCell));
    hashCell->key = key;
    hashCell->val = 0;
    return hashCell;
}

void delete_invalid_protected(CellProtected *pr)
{

    // Si la premiere CellProtected est invalide, on la saute directement
    if (verify(pr->data) == 0)
    {
        pr = pr->next;
    }

    CellProtected *tmp = pr;

    // Sinon si next est valide, on ne fait rien et on la saute sinon
    while (tmp != NULL && tmp->next != NULL)
    {
        if (verify(tmp->next->data) == 0)
        {
            CellProtected **ptr = &tmp;
            tmp = tmp->next->next;
            (*ptr)->next = tmp->next;
        }
        else
        {
            tmp = tmp->next;
        }
    }

    return;
}

int hash_function(Key *key, int size)
{
    // On utilise la fonction de Canthor : pi(x, y) = 1 / 2 * (x + y) * (x + y + 1) + y qui permet un minimum de collisions
    long x = key->val;
    long y = key->n;
    return (1 / 2 * (x + y) * (x + y + 1) + y) % size;
}

int find_position(HashTable *t, Key *key)
{
    int idx = hash_function(key, t->size);
    Key *nullKey = NULL;

    if (t->tab[idx]->key == nullKey || (t->tab[idx]->key->val == key->val && t->tab[idx]->key->n == key->n))
    {
        // Si la cle correspond a l'index hash, il n'y a pas eu collision donc
        // on a trouve l'indice
        return idx;
    }
    else
    {
        // Sinon, on cherche le prochain indice auquel la cle est presente
        int runIdx = (idx + 1) % t->size;
        while (t->tab[runIdx]->key != nullKey && t->tab[runIdx]->key->val == key->val && t->tab[runIdx]->key->n == key->n && runIdx != idx)
        {
            runIdx++;
            runIdx %= t->size;
        }

        // Si on ne trouve toujours pas la cle, elle n'est pas presente dans la table et
        // on retourne donc l'indice auquel elle aurait du etre apres linear probing
        if (runIdx == idx)
        {
            return -1;
        }

        return runIdx;
    }
}

HashTable *create_hashtable(CellKey *keys, int size)
{
    HashTable *hashTable = malloc(sizeof(HashTable));
    hashTable->tab = malloc(sizeof(HashCell) * size);
    HashCell *hashCell;

    // On initialise la table avec des cellules vides
    for (int i = 0; i < size; i++)
    {
        hashTable->tab[i] = create_hashcell(NULL);
    }

    hashTable->size = size;
    int hash;
    while (keys != NULL)
    {
        // On trouve la position a laquelle la cle devrait etre on et y place
        // la cle
        hash = find_position(hashTable, keys->data);
        if (hash != -1)
        {
            hashCell = create_hashcell(keys->data);
            hashTable->tab[hash] = hashCell;
        }

        keys = keys->next;
    }

    return hashTable;
}

void delete_hashtable(HashTable *t)
{
    free(t->tab);
    free(t);
}

Key *compute_winner(CellProtected *decl, CellKey *candidates, CellKey *voters, int sizeC, int sizeV)
{
    HashTable *hashC = create_hashtable(candidates, sizeC);
    HashTable *hashV = create_hashtable(voters, sizeV);
    int count = 0;

    while (decl != NULL)
    {
        if (verify(decl->data) == 1)
        {
            count++;
            int v = find_position(hashV, decl->data->pKey);
            int c = find_position(hashC, str_to_key(decl->data->mess));

            // Si le citoyen est dans la table, et que sa signature est valide, on
            //  incremente val, sauf si val est non nulle (ce qui indique que l'on
            // a deja compte son vote)
            if (v != -1 && hashV->tab[v]->val == 0)
            {
                hashV->tab[v]->val++;
            }
            if (c != -1)
            {
                hashC->tab[c]->val++;
            }
        }
        decl = decl->next;
    }

    int winner = 0;
    Key *winnerKey;
    for (int i = 0; i < sizeC; i++)
    {

        // On boucle sur la table et on cherche la cellule dont val est maximum
        if (hashC->tab[i]->val >= winner)
        {
            winner = hashC->tab[i]->val;
            winnerKey = hashC->tab[i]->key;
        }
    }

    return winnerKey;
}