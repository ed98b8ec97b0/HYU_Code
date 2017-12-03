// #include "miniRSA.h"
#include "my_code.h"
#include <stdio.h>
#include <time.h>
#include <math.h>
#include <stdlib.h>


int main(void)
{
    // uint seed = time(NULL);
    // seed = time(NULL);
    // InitWELLRNG512a(&seed);
    int i;
    // 작은 수 테스트
    // uint op1 = 2, op2 = 31, n = 24342, result;
    // overflow 테스트
    // uint op1 = 3939393939, op2 = 2828282828, rem = 0, result, n = 4040404040;
    // uint op1 = 3939393939, op2 = 3939393939, rem = 0, result, n = 4040404040;
    // 암호화 테스트
    uint op1 = 2018915346, op2 = 609365227, result, n = 3533308591;
    double a, b;

    // my_mul test
    // result = my_mul(op1, op2, n);
    // printf("%u * %u mod %u = %u\n", op1, op2, n, result);

    // my_exp test
    result = my_exp(op1, op2, n);
    printf("%u ^ %u mod %u = %u\n", op1, op2, n, result);
    op1 = my_exp(result, 3403665313, n);
    printf("%u ^ %ld mod %u = %u\n", result, 3403665313, n, op1);

    return 0;
}