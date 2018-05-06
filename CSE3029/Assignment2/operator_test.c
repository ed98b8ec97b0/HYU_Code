#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <limits.h>
#include <math.h>
#include "my_code.h"

#define LOOP 100

double mrand(void) {
    double r;
    
    r = (double)rand() / RAND_MAX;
    r *= (INT_MAX + 1);
    r += (INT_MAX + 1);

    return r;
}

int main(void)
{
    int i;
    uint op1, op2, n, rem, real, mine;
    
    srand(time(NULL));
    op1 = rand();
    op2 = rand();
    n = 13;
    rem = 0;
    
    for (i = 0; i < LOOP; i++)
    {
        op1 = mrand();
        op2 = mrand();

        real = ((op1 % n) + (op2 % n)) % n;
        mine = my_add(op1, op2, n);
        if (real != mine)
        {
            printf("!ADD! (%u, %u): %u != %u\n", op1 % n, op2 % n, real, mine);
        }

        if ((op1 % n) < (op2 % n))
        {
            real = (((op1 % n) + n) - (op2 % n)) % n;
        }
        else
        {
            real = ((op1 % n) - (op2 % n)) % n;
        }
        mine = my_sub(op1, op2, n);
        if (real != mine)
        {
            printf("!SUB! (%u, %u): %u != %u\n", op1 % n, op2 % n, real, mine);
        }

        // real = (uint) pow(op1 % n, op2) % n;
        // mine = my_exp(op1, op2, n);
        // if (real != mine)
        // {
        //     printf("!EXP! %u^%u mod %u: %u != %u\n", op1, op2, n, real, mine);
        // }


        real = (op1 / op2);
        mine = my_div(op1, op2, &rem);
        if (real != mine)
        {
            printf("!DIV! (%u, %u): %u != %u\n", op1 % n, op2 % n, real, mine);
        }
    }


    return 0;
}



