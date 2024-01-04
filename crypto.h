#include <ctype.h>
#include <string.h>

long *encrypt(char *chaine, long s, long n)
{
    /*Encrypte une chaine de characteres avec la cle (s, n).
     */
    long unsigned size = strlen(chaine);
    long *arr = malloc(10 * size);

    for (int i = 0; i < size; i++)
    {
        char c = toupper(chaine[i]);
        /*Convertit chaque charactere en entier puis applique
        une exponentation modulaire.
        */
        int num = c + '0';
        arr[i] = modpow(num, s, n);
    }

    return arr;
}

char *decrypt(long *crypted, int size, long u, long n)
{
    /*Decrypte un tableau d'entiers avec la cle (u, n).
     */
    char *arr = malloc(sizeof(char) * size);

    for (int i = 0; i < size; i++)
    {
        /*Applique l'exponentation modulaire puis convertit le
        resultat en charactere.
        */
        long num = modpow(crypted[i], u, n);
        char c = num - '0';
        arr[i] = tolower(c);
    }

    return arr;
}