#include <stdio.h>
#include <stdlib.h>

long extended_gcd(long s, long t, long *u, long *v)
{
    if (s == 0)
    {
        *u = 0;
        *v = 1;
        return t;
    }

    long uPrim, vPrim;
    long gcd = extended_gcd(t % s, s, &uPrim, &vPrim);
    *u = vPrim - (t / s) * uPrim;
    *v = uPrim;

    return gcd;
}

void generate_keys_values(long p, long q, long *n, long *s, long *u)
{
    *n = p * q;
    long t = (p - 1) * (q - 1);

    /*On applique les formules de l'enonce. On genere autant de valeurs s necessaires
    pour en trouver 1 dont le gcd avec t est 1.
    */

    *s = rand() % t;
    long v;
    long gcd = 0;

    while (gcd != 1)
    {
        *s = rand() % t;
        gcd = extended_gcd(*s, t, u, &v);
    }
}