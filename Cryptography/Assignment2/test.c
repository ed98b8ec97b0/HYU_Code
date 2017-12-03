// #include "miniRSA.h"
// #include "my_code.h"
#include <stdio.h>
#include <time.h>
#include <math.h>
#include <stdlib.h>

typedef unsigned int uint;

uint my_div(uint numer, uint denom, uint *rem)
{
    uint n_bit = 1, d_bit = 1, p_bit, quot = 0;
    uint n_tmp, d_tmp;

    if (numer == 0)
    {
        return 0;
    }

    n_tmp = numer;
    while (n_tmp > 1)
    {
        n_tmp >>= 1;
        n_bit++;
    }
    d_tmp = denom;
    while (d_bit > 1)
    {
        d_tmp >>= 1;
        d_bit++;
    }
    if (n_bit < d_bit)
    {
        *rem = numer;
        return 0;
    }

    p_bit = n_bit - d_bit;
    printf("n_bit = %u, d_bit = %u\n", n_bit, d_bit);
    n_tmp = numer;
    d_tmp = denom;
    while (n_tmp >= d_tmp)
    {
        d_tmp <<= p_bit;
        printf("d_tmp = %u\n", d_tmp);
        if (n_tmp > d_tmp)
        {
            n_tmp -= d_tmp;
            quot += 1 << p_bit;
        }
        p_bit--;
    }

    *rem = n_tmp;
    return quot;
}

int main(void)
{
    // uint seed = time(NULL);
    // seed = time(NULL);
    // InitWELLRNG512a(&seed);
    int i;
    uint test = 2, test2, rem = 0, quot;
    double a, b;
    
    quot = my_div(51, 2, &rem);
    printf("50 = 2 * %u + %u\n", quot, rem);
    return 0;
}