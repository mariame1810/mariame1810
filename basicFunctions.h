#include <stdbool.h>

int is_prime_naive(long p)
{
    /*Teste si l'entier p est premier par la methode modpow_naive
    Boucle sur tous les entiers de 2 a p - 1 et en sort des que i
    divise p.
    */
    for (int i = 2; i < p; ++i)
    {
        if (p % i == 0)
        {
            return 0;
        }
    }

    return 1;
}

long modpow_naive(long a, long m, long n)
{
    /*Exponentiation modulaire naive
    Multiplie a m fois et calcule son reste modulo n
    */
    long apow = a;
    for (int i = 0; i < m; ++i)
    {
        apow = apow * a;
    }

    return apow % n;
}

int modpow(long a, long m, long n)
{
    /*Exponentiation modulaire avec recurrence, en se basant sur le fait que
    a^m = a^(m/2) * a^(m/2) si m est pair
    a^m = a^((m - 1)/2) * a^((m - 1(/2) * a si m est impair.
    Ici, m/2 si m est impair est equivalent a [m/2]
    */
    if (a == 0)
    {
        return 0;
    }
    if (m == 0)
    {
        return 1;
    }
    long y;
    if (m % 2 == 0)
    {
        y = modpow(a, m / 2, n);
        y = (y * y) % n;
    }
    else
    {
        y = a % n;
        y = (y * modpow(a, m - 1, n) % n) % n;
    }

    return (int)((y + n) % n);
}