#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void memory_swap(void *x, void *y, int n);

int main() {
    int a = 100, b = 200;

    printf("a = %d // b = %d --- in main\n", a, b);
    memory_swap(&a, &b, sizeof(int));
    printf("a = %d // b = %d --- in main\n", a, b);

    return 0;
}

void memory_swap(void *x, void *y, int n) {
    void* temp = malloc(n);

    printf("x = %d // y = %d --- in memory_swap\n", *(int*) x, *(int*) y);
    memcpy(temp, x, n);
    memcpy(x, y, n);
    memcpy(y, temp, n);
    printf("x = %d // y = %d --- in memory_swap\n", *(int*) x, *(int*) y);

    free(temp);
}
