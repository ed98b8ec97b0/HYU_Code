#include "op.h"

// 모듈러 연산
uint my_mod(uint op, uint n)
{
    uint rem = 0; // 나머지를 받아오기 위한 포인터
    uint tmp; // 나눗셈시 return값을 받기 위한 임시 값.

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
    uint tmp = denom;
    uint quot = 1;

    /*
    나눠지는 수가 나누는 수보다 작아서 더이상 나눌 수가 없는 상황.
    이때, numer가 나머지이다.
    */
    if (numer < denom) {
        *rem = numer;
        return 0;
    }

    /*
    나눠지는 수와 나누는 수가 같아 몫 = 1, 나머지는 0인 상황.
    */
    if (numer == denom) {
        *rem = 0;
        return 1;
    }
 
    /*
    시프트 연산자로 나눌 때 2의 거듭 제곱수로만 나눌 수 있다.
    2의 거듭제곱수 중 가능한 가장 큰 수부터 몫으로 하여 나누는 수에 곱해 나눠지는 수에 뺀 값을 다시 나눠지는 수로 쓴다.
    이를 위의 종료 조건까지 반복하여 나오는 모든 몫을 전부 더하면 그게 실제 몫이 된다.
    */
    while (denom <= numer) {
        quot <<= 1;
        denom <<= 1;
    }
    if (numer < denom) {
        quot >>= 1;
        denom >>= 1;
    }
    quot += my_div(numer - denom, tmp, rem);
    
    return quot;
}

// 덧셈 연산
uint my_add(uint op1, uint op2, uint n)
{
    uint result;

    // 오버플로우 방지를 위한 모듈러 연산.
    op1 = my_mod(op1, n);
    op2 = my_mod(op2, n);

    // 덧셈 연산 후에도 모듈러 값보다 커질 수 있기 때문에 다시 한번 더 모듈러 연산을 수행한다.
    result = my_mod(op1 + op2, n);

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
        op1 += n;
    }

    result = op1 - op2;
    result = my_mod(result, n);

    return result;
}

// 모듈러 곱셈 연산
uint my_mul(uint op1, uint op2, uint n)
{
    uint result = 0;

    // 오버플로우 방지를 위한 모듈러 연산.
    op1 = my_mod(op1, n);
    op2 = my_mod(op2, n);

    // 매 반복마다 덧셈을 수행하여 오버플로우를 방지한다.
    uint tmp = op2;
    for (int i = 0; i < tmp; i++)
    {
        result += op1;
        result = my_mod(result, n);
    }

    return result;
}