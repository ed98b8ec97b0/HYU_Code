#include "mergesort.h"

void wrt(int key[], int sz) {
    for (int i = 0; i < sz; i++) 
        printf("%4d%s", key[i], ((i < sz-1) ? " " : "\n"));
}