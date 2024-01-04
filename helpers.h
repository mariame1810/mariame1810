#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int witness( long a , long b , long d , long p ) {
    long x = modpow(a ,d , p ) ;
    if( x == 1) {
    return 0;
    }
    for( long i = 0; i < b ; i ++) {
    if( x == p -1) {
    return 0;
    }
    x = modpow(x ,2 , p ) ;
    }
    return 1;
}

long rand_long( long low , long up ) {
    return rand() % ( up - low +1) + low ;
}

int is_prime_miller( long p , int k ) {
    if ( p == 2) {
    return 1;
    }
    if (!( p & 1) || p <= 1) { //on verifie que p est impair et different de 1
    return 0;
    }
    //on determine b et d :
    long b = 0;
    long d = p - 1;
    while (!( d & 1) ) { //tant que d n’est pas impair
    d = d /2;
    b = b +1;
    }
    // On genere k valeurs pour a, et on teste si c’est un temoin :
    long a ;
    int i ;
    for( i = 0; i < k ; i ++) {
        a = rand_long(2 , p -1) ;
        if( witness(a ,b ,d , p ) ) {
        return 0;
        }
    }
    return 1;
}

long random_prime_number(int low_size, int up_size, int k){
    long lowerBound = pow(2, low_size - 1);
    long upperBound = pow(2, up_size) - 1;
    
    long randPrime = rand_long(lowerBound, upperBound);
    
    /*On genere autant de nombres aleatoires necessaires pour en trouver 1 qui soit
    premier, via le test de miller.
    */
    
    while (is_prime_miller(randPrime, k) == 0){
        randPrime = rand_long(lowerBound, upperBound);
    }
    
    return randPrime;
}

void print_long_vector( long* result , int size ){
    printf("Vector : [ ");
    for (int i = 0; i < size; i++){
        printf("%lx \t ", result[i]);
        
    }
    printf("] \n") ;
} 