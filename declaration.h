int *select_random_indices(int nv, int nc)
{
    int *indices = malloc(nv * sizeof(int));
    for (int i = 0; i < nv; i++)
    {
        indices[i] = i;
    }

    int *selected_indices = malloc(nc * sizeof(int));

    int maxIndex = nv - 1;
    int tmp;

    while (maxIndex >= nv - nc)
    {
        int num = rand() % maxIndex;
        tmp = indices[num];
        indices[num] = indices[maxIndex];
        selected_indices[nv - maxIndex - 1] = tmp;
        indices[maxIndex] = tmp;
        maxIndex = maxIndex - 1;
    }

    free(indices);

    return selected_indices;
}

void generate_random_data(int nv, int nc)
{

    struct Key *pKeys = malloc(nv * sizeof(Key));
    struct Key *sKeys = malloc(nv * sizeof(Key));

    FILE *fKeyptr, *fCandidatesPtr, *fDeclarationsPtr;
    ;
    fKeyptr = fopen("./keys.txt", "w");
    fCandidatesPtr = fopen("./candidates.txt", "w");
    fDeclarationsPtr = fopen("./declarations.txt", "w");

    struct Key pKey;
    struct Key sKey;

    for (int i = 0; i < nv; i++)
    {
        init_pair_keys(&pKey, &sKey, 3, 7);
        pKeys[i] = pKey;
        sKeys[i] = sKey;
        fprintf(fKeyptr, "%s %s\n", key_to_str(&pKey), key_to_str(&sKey));
    }

    fclose(fKeyptr);

    int *selected_candidates = select_random_indices(nv, nc);

    for (int j = 0; j < nc; j++)
    {
        fprintf(fCandidatesPtr, "%s\n", key_to_str(&pKeys[selected_candidates[j]]));
    }

    fclose(fCandidatesPtr);

    for (int k = 0; k < nv; k++)
    {
        int voteFor = selected_candidates[rand() % nc];
        char *keyVote = key_to_str(&pKeys[voteFor]);
        Signature *sgn = sign(keyVote, &sKeys[k]);
        Protected *pr = init_protected(&pKeys[k], keyVote, sgn);
        fprintf(fDeclarationsPtr, "%s\n", protected_to_str(pr));
    }
    fclose(fDeclarationsPtr);
}