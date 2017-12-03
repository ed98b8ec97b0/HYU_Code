#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <unistd.h>

#include "my_code.h"

extern int check;

// 모듈러 연산
uint my_mod(uint op, uint n)
{
    uint rem = 0; // 나머지를 받아오기 위한 포인터
    uint tmp; // 나눗셈시 return값을 받기 위한 임시 값.

    if (op == 0)
    {
        return 0;
    }

    // 모듈러 연산을 할 필요가 없는 경우.
    if (op < n) 
    {
        return op;
    }

    // 나눗셈 연산에 모듈러값과 피연산자를 넣는다.
    tmp = my_div(op, n, &rem);
    op = rem; // 나눗셈 연산을 하며 *rem에 저장된 값이 바로 모듈러 연산 후의 값.
    return op;
}

// 나눗셈 연산
uint my_div(uint numer, uint denom, uint *rem)
{
    uint n_bit = 0, d_bit = 0, p_bit, quot = 0;
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
    while (d_tmp > 1)
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
    n_tmp = numer;
    while (n_tmp > denom)
    {
        d_tmp = denom;
        d_tmp <<= p_bit;
        if (n_tmp > d_tmp)
        {
            n_tmp -= d_tmp;
            quot += 1 << p_bit;
        }
        p_bit--;
    }
    if (n_tmp == denom)
    {
        n_tmp -= denom;
        quot++;
    }

    *rem = n_tmp;
    return quot;
}

// 덧셈 연산
uint my_add(uint op1, uint op2, uint n)
{
    uint result;

    // 오버플로우 방지를 위한 모듈러 연산.
    op1 = my_mod(op1, n);
    op2 = my_mod(op2, n);

    op2 = n - op2;
    if (op1 > op2)
    {
        result = my_mod(op1 - op2, n);
    }
    else if (op1 < op2)
    {
        result = my_mod(n - op2 + op1, n);
    }
    else
    {
        result = 0;
    }
    // 덧셈 연산 후에도 모듈러 값보다 커질 수 있기 때문에 다시 한번 더 모듈러 연산을 수행한다.

    return result;
}

// 뺄셈 연산
uint my_sub(uint op1, uint op2, uint n) 
{
    uint result;

    // 오버플로우 방지를 위한 모듈러 연산.
    op1 = my_mod(op1, n);
    op2 = my_mod(op2, n);

    /*
    작은 수에서 큰 수를 빼는 경우.
    작은 수에 모듈러 값을 더해주는 것으로 똑같은 결과값을 가져올 수 있다.
    */
    if (op1 < op2)
    { 
        op2 = n - op2;
    }

    result = op1 + op2;
    result = my_mod(result, n);

    return result;
}

uint my_mul(uint op1, uint op2, uint n)
{
    uint result = 0, cal = 0;;

    // 시작전 들어온 값들 모듈러 연산.
    op1 = my_mod(op1, n);
    op2 = my_mod(op2, n);

    // 
    while (op2 > 0)
    {
        if (op2 & 1)
        {
            result = my_add(result, op1, n);
            op2 -= 1;
        }
        op1 = my_add(op1, op1, n);
        op2 >>= 1;
    }
    
    return result;
}

// 모듈러 제곱 연산
uint my_exp(uint op1, uint op2, uint n)
{
    uint i = 1;
    uint result = 1;
    uint t_op1 = 1, t_op2 = 1;

    // 모듈러 연산 실행.
    op1 = my_mod(op1, n);
    op2 = my_mod(op2, n);

    // 1. op1을 가능한 한 거듭제곱한다. 제곱할 때마다 지수인 i는 1부터 시작하여 2배씩 증가한다.
    // 2. 더이상 거듭제곱할 수 없다면 result에 곱하고 다시 1제곱으로 돌아간다. 또한 i도 다시 1로 돌아간다.
    // 3. op2 - i를 통해 남은 지수를 변경한다.
    // 4. 1~3 과정을 op2가 0이 될 때까지 반복한다.
    while (op2 > 0)
    {
        // 실제 계산에는 op1을 이용하지 않고 t_op1을 사용한다.
        t_op1 = op1;
        i = 1;
        if (op2 == 1)
        {   
            result = my_mul(result, t_op1, n);
            op2 -= 1;
            break;
        }

        while ((i * 2) <= op2)
        {
            t_op1 = my_mul(t_op1, t_op1, n);
            i <<= 1;
            if (i == 0)
            {
                exit(1);
            }
            if (i == (1<<31))
            {
                break;
            }
        }
        result = my_mul(result, t_op1, n);
        op2 -= i;
        
    }

    return result;
}

// 일반 제곱연산
uint my_pow(uint op1, uint op2)
{
    uint result = 1, cal = 1, rem = 0, tmp;
    int i = 1;
    if (op2 == 0)
    {
        return 1;
    }
    if (op2 == 1)
    {
        return op1;
    }

    // i와 op2가 같을 때 까지 반복
    while (i != op2)
    {
        i = 1;
        cal = op1;
        // Square and multiply
        while (i < op2)
        {
            tmp = cal;
            cal *= tmp;
            i *= 2;

            if (i * 2 > op2)
            {
                break;
            }
        }

        // for loop에서 계산한 값을 result에 쌓음.
        result *= cal;

        // op2 = 2^n + 1이라면 한번 더 곱하고 while문 탈출.
        if ((op2 - i) == 1)
        {
            result *= op1;
            break;
        }

        /*
        i > op2, 다시 한번더 square and multiply를 작동.
        이때, i를 다시 1/2시켜주고 그 뒤 op2-i를 이용해 재반복.
        */
        if (i != op2)
        {
            op2 -= i;
        }
        
    }
    
    return result;
}

// n - 1을 2^r * d 꼴로 바꿨을 때의 d, r을 찾아주는 함수.
uint reform(uint n, uint *d)
{
    uint r = 0, rem = 0, denom = 1, quot, expo;
    my_div(n, 2, &rem);
    if (rem == 1)
    {
        printf("홀수만이 입력되어야합니다. 입력 오류\n");
        exit(1);
    }
    while(denom < n)
    {
        denom *= 2;
        r++;
        quot = my_div(n, denom, &rem);
        if (rem == 0)
        {
            my_div(quot, 2, &rem);
            {
                if (rem == 1)
                {
                    *d = quot;
                    expo = r;
                }
            }
        }
    }

    return expo;   
}