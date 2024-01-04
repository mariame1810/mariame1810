#include <string.h>

typedef struct Key
{
    long val;
    long n;
} Key;

void init_key(Key *key, long val, long n)
{
    key->val = val;
    key->n = n;
}

void init_pair_keys(Key *pKey, Key *sKey, long low_size, long up_size)
{
    // On choisit aleatoirement deux entiers p et q
    long p = random_prime_number(low_size, up_size, 50000);
    long q = random_prime_number(low_size, up_size, 50000);

    while (p == q)
    {
        // Rechoisit q tant que p et q sont egaux
        q = random_prime_number(low_size, up_size, 5000);
    }

    long n = 0, s = 0, u = 0;
    generate_keys_values(p, q, &n, &s, &u);

    if (u < 0)
    {
        // Si u est negatif, on reajoute t
        long t = (p - 1) * (q - 1);
        u = u + t;
    }

    init_key(pKey, s, n);
    init_key(sKey, u, n);
}

char *key_to_str(Key *key)
{
    long x = key->val;
    long y = key->n;

    // On choisit arr assez grand
    char *arr = malloc(156);

    sprintf(arr, "(%lx,%lx)", x, y);

    // On reduit la taille de arr selon la taille du string qu'il contient
    arr = realloc(arr, strlen(arr));
    return arr;
}

Key *str_to_key(char *str)
{
    long x, y;
    sscanf(str, "(%lx,%lx)", &x, &y);

    Key *key = malloc(sizeof(Key));
    init_key(key, x, y);

    return key;
}