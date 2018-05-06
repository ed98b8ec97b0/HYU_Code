#include <stdio.h>

#define N 16

void bubble(int a[], int n);
void swap(int *, int *);

int main(void)
{
    int i;
    int key[] = {4, 3, 1, 67, 55, 8, 0, 4, -5, 37, 7, 4, 2, 9, 1, -1};

    printf("%s\n", "before sorting:\n");
    for (i = 0; i < N; i++)
    {
        printf("%4d", key[i]);
    }
    printf("\n\n");
    bubble(key, N);
    printf("%s\n", "after sorting:\n");
    for (i = 0; i < N; i++)
    {
        printf("%4d", key[i]);
    }
    printf("\n\n");

    return 0;
}

void bubble(int a[], int n)
{
    int i, j;

    for (i = 0; i < (n - 1); ++i)
    {
        for (j = (n - 1); j > i; --j)
        {
            if (a[j - 1] > a[j])
            {
                swap(&a[j - 1], &a[j]);
            }
        }
    }
}

void swap(int *p, int *q)
{
    int tmp;

    tmp = *p;
    *p = *q;
    *q = tmp;
}