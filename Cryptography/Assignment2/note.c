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
    // 3. 1~3 과정을 op2가 0이 될 때까지 반복한다.
    while (op2 > 0)
    {
        // 실제 계산에는 op1을 이용하지 않고 t_op1을 사용한다.
        t_op1 = op1;
        i = 1;
        if (op2 == 1)
        {
            result = my_mul(result, t_op1, n);
            break;
        }

        while (i < op2)
        {
            t_op1 = my_mul(t_op1, t_op1, n);
            i = my_mul(i, 2, n);
        }
        result = my_mul(result, t_op1, n);
        op2 -= i;
    }

    return result;
}