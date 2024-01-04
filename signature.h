#include "crypto.h"

typedef struct signature {
    long* content;
    long unsigned size;
} Signature;

Signature* init_signature(long* content, int size) {
    Signature* sgn = malloc(sizeof(Signature));
    sgn->content = malloc(size * sizeof(long));
    sgn->size = size;
    
    for (int i = 0; i < size; i++) {
        sgn->content[i] = content[i];
    }
    
    return sgn;
}

Signature* sign(char* mess, Key* sKey) {
    long s = sKey->val;
    long n = sKey->n;
    
    //On encrypte le message de la signature
    long* content = encrypt(mess, s, n);
    Signature* sgn = init_signature(content, strlen(mess));
    return sgn;
}

//Deja implemente
char* signature_to_str(Signature* sgn) {
    char* result = malloc(10*sgn->size*sizeof(char));
    result[0]= '#';
    int pos = 1;
    char buffer[156];
    for (int i = 0; i < sgn->size; i++) {
        sprintf (buffer, "%lx", sgn->content[i]);
        for (int j = 0; j < strlen(buffer); j++) {
            result[pos] = buffer[j];
            pos = pos +1;
        }
        result[pos] = '#';
        pos = pos + 1;
    }
    result[pos] = '\0' ;
    result = realloc(result, (pos + 1)*sizeof(char));
    return result ;
}

Signature* str_to_signature(char* str) {
    int len = strlen(str);
    long* content = (long*)malloc(sizeof(long)*len);
    int num = 0;
    char buffer [256];
    int pos = 0;
    for (int i = 0; i < len; i++) {
        if(str[i] != '#') {
            buffer[pos] = str[i];
            pos = pos + 1;
        } else {
        if (pos != 0) {
            buffer[pos] = '\0';
            sscanf(buffer, "%lx", &(content[num]));
            num = num + 1;
            pos = 0;
            }
        }
    }
    content = realloc(content, num*sizeof(long));
    return init_signature(content, num);
}