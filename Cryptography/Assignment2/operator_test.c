#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <limits.h>
#include "op.h"

double mrand(void) {
    double r = (double)rand() / RAND_MAX;
    r += (INT_MAX + 1);

    return r;
}

int main(void)
{
    srand(time(NULL));

    uint a, b, c, n = 20, d = 0;

    for (int i = 0; i < 5; i++) {
        a = rand();
        b = rand();

        a %= n;
        a += 1;
        b %= n;
        b += 1;
        if (a >= b)
        {
            c = a - b;
            c %= n;
        }
        else
        {
            c = a + n - b;
            c %= n;
        }

        printf("a = %u, b = %u, n = %u\n", a, b, n);
        printf("'+': %2u %2u\t", (a + b) % n, my_add(a, b, n));
        printf("'-': %2u %2u\t", c, my_sub(a, b, n));
        printf("'*': %2u %2u\t", (a * b) % n, my_mul(a, b, n));
        printf("'/': %2u %2u\n\n", (a / b) % n, my_div(a, b, &d));
    }

    return 0;
}



