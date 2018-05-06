#include <stdio.h>

void swap(int *p, int *q) {
    int tmp;

    tmp = *p;
    *p = *q;
    *q = tmp;
}

int main(void) {
    int i = 3, j = 5, *p = &i, *q = &j;

    printf("p: %d, q: %d\n", *p, *q);
    swap(&i, &j);
    printf("p: %d, q: %d\n", *p, *q);

    return 0;
}