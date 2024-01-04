typedef struct Protected
{
    Key *pKey;
    char *mess;
    Signature *sgn;
} Protected;

Protected *init_protected(Key *pKey, char *mess, Signature *sgn)
{
    Protected *prot = malloc(sizeof(Protected));
    prot->pKey = pKey;
    prot->mess = mess;
    prot->sgn = sgn;

    return prot;
}

int verify(Protected *pr)
{
    char *decrypted = decrypt(pr->sgn->content, pr->sgn->size, pr->pKey->val, pr->pKey->n);

    /*On boucle sur les entier de la signature, les decrypte et
    sort de la boucle des que l'on trouve une difference avec le contenu
    de la signature
    */
    for (int i = 0; i < pr->sgn->size; i++)
    {
        if (decrypted[i] != pr->mess[i])
        {
            return 0;
        }
    }

    return 1;
}

char *protected_to_str(Protected *prot)
{
    char *key = key_to_str(prot->pKey);
    char *sgn = signature_to_str(prot->sgn);

    // On choisit la taille de arr selon la taille de key, mess et sgn
    char *arr = malloc((strlen(key) + strlen(prot->mess) + strlen(sgn) + 3) * sizeof(char));

    sprintf(arr, "%s %s %s", key, prot->mess, sgn);

    return arr;
}

Protected *str_to_protected(char *chaine)
{
    char *strKey = malloc(strlen(chaine) * sizeof(char));
    char *mess = malloc(strlen(chaine) * sizeof(char));
    char *strSgn = malloc(strlen(chaine) * sizeof(char));

    sscanf(chaine, "%s %s %s", strKey, mess, strSgn);

    strKey = realloc(strKey, strlen(strKey) * sizeof(char));
    mess = realloc(mess, strlen(mess) * sizeof(char));
    strSgn = realloc(strSgn, strlen(strSgn) * sizeof(char));

    Key *key = str_to_key(strKey);
    Signature *sgn = str_to_signature(strSgn);

    Protected *prot = init_protected(key, mess, sgn);
    return prot;
}