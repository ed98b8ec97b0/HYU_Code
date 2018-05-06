#include "mergesort.h"

void my_mergesort(int key[], int n) {
    int m, k, j, *w;

    for (m = 1; m < n; m *= 2)
        ;
    if (n < m) {
        printf("%s\n", "ERROR: Array size is not a power of 2 -- bye!");
        exit(1);
    }
    w = calloc(n, sizeof(int));
    assert(w != NULL);
    for (k = 1; k < n; k *= 2) {
        for (j = 0; j < n - k; j += 2 * k)
            my_merge(key+j, key+j+k, w+j, k, k);
        for (j = 0; j < n; j++)
            key[j] = w[j];
    }
    free(w);
}