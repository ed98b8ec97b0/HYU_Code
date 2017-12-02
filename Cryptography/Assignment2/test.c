#include "miniRSA.h"
#include "my_code.h"
#include <stdio.h>
#include <time.h>
#include <math.h>
#include <stdlib.h>

int check = 0;

uint ModMul(uint x, uint y, uint n)
{
    uint result;

    x = my_mod(x, n);
    y = my_mod(y, n);
    result = x * y;
    result = my_mod(result, n);

    return result;
}

bool IsPrime(uint testNum, uint repeat)
{
    int i, j;
    double random;
    bool result = TRUE, possible;
    uint a = 1, x, k, q = 0, b, tmp;
    uint seed = time(NULL);
    seed = time(NULL);

    k = reform(testNum - 1, &q);

    for (int i = 0; i < repeat; i++)
    {
        random = 0;
        a = 0;
        possible = FALSE;

        while (a < 2)
        {
            random = WELLRNG512a() * (testNum - 1);
            a = (uint)random;
        }

        tmp = my_exp(a, q, testNum);
        if (tmp == 1)
        {
            possible = TRUE;
            continue;
        }

        for (j = 0; j < k; j++)
        {
            tmp = my_exp(a, my_pow(2, j) * q, testNum);   
            if (tmp == (testNum - 1))
            {   
                possible = TRUE;
                break;
            }
        }
        if (possible == FALSE)
        {
            result = FALSE;
        }
    }

    return result;
}

// bool IsPrime(uint testNum, uint repeat)
// {
//     bool result = TRUE;
//     if (testNum <= 1 || testNum == 4)
//         return result = FALSE;
//     if (testNum <= 3)
//         return result = TRUE;

//     uint q = testNum - 1;
//     while (my_mod(q, 2) == 0)
//         q >>= 1;

//     uint cnt;
//     for (cnt = 0; cnt < repeat; cnt++)
//     {
//         bool flag = FALSE;
//         uint a = (uint)(WELLRNG512a() * -1U);
//         a = my_mod(a, (testNum - 3)) + 2;

//         uint val = my_exp(a, q, testNum);
//         if (val == 1 || val == testNum - 1)
//             continue;

//         while (q != testNum - 1)
//         {
//             q = ModMul(q, 2, testNum);
//             val = ModMul(val, val, testNum);

//             if (val == 1)
//                 return result = FALSE;
//             if (val == testNum - 1)
//             {
//                 flag = TRUE;
//                 break;
//             }
//         }
//         if (flag == FALSE)
//             return result = FALSE;
//     }

//     return result;
// }

// bool IsPrime(uint testNum, uint repeat)
// {
//     int i, j;
//     double random;
//     bool result = TRUE, posibility;
//     uint a = 1, x, r, d = 0, b, c;

//     r = reform(testNum - 1, &d);

//     for (i = 0; i < repeat; i++)
//     {
//         posibility = FALSE;
//         // 1 < a < n-1 인 a 생성.
//         while (a < 2)
//         {
//             random = WELLRNG512a() * (testNum - 1);
//             a = (uint)random;
//         }

//         x = my_exp(a, d, testNum);
//         if (x == 1)
//         {
//             posibility = TRUE;
//             continue;
//         }

//         for (j = 0; j < r - 1; j++)
//         {
//             x = my_exp(x, 2, testNum);

//             if (x == testNum - 1)
//             {
//                 posibility = TRUE;
//                 break;
//             }
//         }
//         if (posibility == FALSE)
//         {
//             result = FALSE;
//         }
        
//     }

//     return result;;
// }

// bool IsPrime(uint testNum, uint repeat)
// {
//     int j;
//     double random;
//     bool result = TRUE;
//     uint a = 1, x, k, q = 0, b, tmp;

//     k = reform(testNum - 1, &q);
    
//     for (int i = 0; i < repeat; i++)
//     {
//         bool posibility = FALSE;
//         while (a < 2)
//         {
//             random = WELLRNG512a() * (testNum - 1);
//             a = (uint)random;
//         }
    
//         tmp = my_exp(a, q, testNum);
//         if (tmp == 1)
//         {
//             result = TRUE;
//         }

//         for (j = 0; j < k; j++)
//         {
//             tmp = my_pow(2, j);
//             tmp *= q;
//             tmp = my_exp(a, q, testNum);
//             if (tmp == (testNum - 1))
//             {
//                 result = TRUE;
//             }
//         }
//     } 

//     return result;   
// }

int main(void)
{
    uint seed = time(NULL);
    seed = time(NULL);
    InitWELLRNG512a(&seed);
    int i;
    uint test = 2, test2, rem = 0;
    bool result;
    double a, b;

    for (int i = 10; i < 100; i++)
    {
        test = i;
        if (test % 2 == 1)
        {
            result = IsPrime(test, 1000);
            if (result == TRUE)
            {
                printf("%u ", test);
            }
        }
    }
    printf("\n");

    // uint a = 11, b = 13, c;
    // c = my_pow(11, 13);
    // c = my_mod(c, 27);
    // printf("c = %u\n", c);

    // double b;
    // uint n, d = 0, r = 0, a;
    // b = WELLRNG512a() * 100000;
    // n = (uint) b;
    // r = reform(n-1, &d);
    // printf("r = %u / d = %u\n", r, d);
    // a = my_pow(2, r) * d;
    // printf("%u vs %u\n", n, a + 1);

    return 0;
}