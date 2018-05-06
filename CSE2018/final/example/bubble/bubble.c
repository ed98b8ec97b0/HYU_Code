#include <stdio.h>

void swap (int *p, int *q) {
    int tmp = *p;

    *p = *q;
    *q = tmp;
}

void bubble(int a[], int n) {
    int i, j;

    for (i = 0; i < n-1; ++i) {
        for (j = n-1; j > i; --j) {
            if (a[j-1] > a[j]) {
                swap(&a[j-1], &a[j]);
            }
        }
    }
}

int main(void)
{
    int i = -1, a[10];

    while (++i < 10) {
        scanf("%d", &a[i]);
    }
    bubble(a, 10);
    for (int i = 0; i < 10; i++) {
        printf("%d ", a[i]);
    }
    printf("\n");

    return 0;
}