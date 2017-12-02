#include "miniRSA.h"
#include "my_code.h"
#include <stdio.h>
#include <time.h>
#include <math.h>
#include <stdlib.h>

int check = 0;

bool IsPrime(uint testNum, uint repeat)
{
    int j;
    double random;
    uint a = 1, x, k, q = 0, b, tmp;

    k = reform(testNum - 1, &q);
    while (a < 2)
    {
        random = WELLRNG512a() * (testNum - 1);
        a = (uint)random;
    }

    tmp = my_pow(a, q);
    tmp = my_mod(tmp, testNum);
    if (tmp == 1)
    {
        return FALSE;
    }

    for (j = 0; j < k -1; j++)
    {
        tmp = my_pow(2, j);
        tmp *= q;
        tmp = my_pow(a, tmp);
        tmp = my_mod(tmp, testNum);
        if (tmp == (testNum - 1))
        {
            return FALSE;
        }
    }
    return TRUE;
}

int main(void)
{
    int i;
    uint test = 2, test2, rem = 0;
    bool result;
    double a, b;
    uint seed = time(NULL);
    seed = time(NULL);
    InitWELLRNG512a(&seed);

    a = WELLRNG512a();
    a *= my_pow(10,9);
    test = (uint) a;

    b = WELLRNG512a();
    b *= my_pow(10, 6);
    test2 = (uint)b;

    test2 = my_div(test, test2, &rem);
    result = IsPrime(test, 5);
    if (result == TRUE)
    {
        printf("prime!\n");
    }
    else
    {
        printf("not prime!\n");
    }

    // for (i = 0; i < 1; i++)
    // {

    //     while (test % 2 == 0)
    //     {
    //         a = WELLRNG512a();
    //         a *= my_pow(10,9);
    //         test = (uint) a;
    //     }
    //     result = IsPrime(test, 1);
    //     if (result == TRUE)
    //     {
    //         printf("%u is prime.\n", test);
    //     }
    //     else
    //     {
    //         printf("%u isn't prime.\n", test);
    //     }
    // }
    // printf("\n");

    // uint seed = time(NULL);
    // seed = time(NULL);
    // InitWELLRNG512a(&seed);
    // double a;
    // uint b;
    // uint c = 100000;
    // for (int i = 0; i < 200; i++)
    // {
    //     a = (WELLRNG512a() * c);
    //     b = (uint) a;
    //     printf("%u\n", b);
    // }

    // test = my_fow(10, 3);
    // printf("10^3 = %u\n", test);
    // test = my_fow(2, 10);
    // printf("2^10 = %u\n", test);
    // test = my_fow(10, 6);
    // printf("10^6 = %u\n", test);
    // test = my_fow(2, 20);
    // printf("2^20 = %u\n", test);
    // test = my_fow(10, 9);
    // printf("10^9 = %u\n", test);
    // test = my_fow(2, 30);
    // printf("2^30 = %u\n", test);

    return 0;
}