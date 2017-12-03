// #include "miniRSA.h"
#include "my_code.h"
#include <stdio.h>
#include <time.h>
#include <math.h>
#include <stdlib.h>

uint ModInv(uint a, uint m)
{
    uint result;
    uint p1 = 0, p2 = 1, p3, quot = 0, rem = 0, b = m;

    while (b != 1)
    {
        quot = my_div(b, a, &rem);
        b = a;
        a = rem;
        p3 = my_sub(p1, my_mul(p2, quot, m), m);
        p1 = p2;
        p2 = p3;
    }
    result = p3;

    return result;
}

int main(void)
{
    // uint seed = time(NULL);
    // seed = time(NULL);
    // InitWELLRNG512a(&seed);
    int i;
    // 작은 수 테스트
    // uint op1 = 2, op2 = 31, n = 24342, result;
    // overflow 테스트
    uint op1 = 3939393939, op2 = 2828282828, rem = 0, result, n = 4040404040;
    // uint op1 = 3939393939, op2 = 3939393939, rem = 0, result, n = 4040404040;
    // 암호화 테스트
    // uint op1 = 2018915346, op2 = 609365227, result, n = 3533308591;
    // uint op1 = 866844683, n = 2065285980, result;
    // uint op1 = 240, n = 
    double a, b;

    // my_div test
    result = my_div(op1, op2, &rem);
    printf("result = %u\n", result);    


    // my_add & my_sub test
    // result = my_add(op1, op2, n);
    // result = my_add(op2, op1, n);
    // result = my_sub(op1, op2, n);
    // result = my_sub(op2, op1, n);

    // my_mul test
    // result = my_mul(op1, op2, n);
    // printf("%u * %u mod %u = %u\n", op1, op2, n, result);

    // my_exp test
    // result = my_exp(op1, op2, n);
    // printf("%u ^ %u mod %u = %u\n", op1, op2, n, result);
    // op1 = my_exp(result, 3403665313, n);
    // printf("%u ^ %ld mod %u = %u\n", result, 3403665313, n, op1);

    // ModInv test
    result = ModInv(op1, n);
    printf("ModInv(%u, %u) = %u\n", op1, n, result);

    return 0;
}