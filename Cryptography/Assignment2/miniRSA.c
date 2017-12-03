/*
 * @file    rsa.c
 * @author  작성자 이름 / 학번
 * @date    작성 일자
 * @brief   mini RSA implementation code
 * @details 세부 설명
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "miniRSA.h"

// +, -, *, /, % 연산 구현 헤더
#include "my_code.h"

// math.h
#include <math.h>

uint p, q, e, d, n;

/*
 * @brief     모듈러 덧셈 연산을 하는 함수.
 * @param     uint a     : 피연산자1.
 * @param     uint b     : 피연산자2.
 * @param     byte op    : +, - 연산자.
 * @param     uint n      : 모듈러 값.
 * @return    uint result : 피연산자의 덧셈에 대한 모듈러 연산 값. (a op b) mod n
 * @todo      모듈러 값과 오버플로우 상황을 고려하여 작성한다.
 */

uint ModAdd(uint a, uint b, byte op, uint n) {
    uint result = 0; 
    
    switch (op)
    {
        case '+':
            result = my_add(a, b, n);
            break;
        case '-':
            result = my_sub(a, b, n);
            break;
    }

    return result;
}

/*
 * @brief      모듈러 곱셈 연산을 하는 함수.
 * @param      uint x       : 피연산자1.
 * @param      uint y       : 피연산자2.
 * @param      uint n       : 모듈러 값.
 * @return     uint result  : 피연산자의 곱셈에 대한 모듈러 연산 값. (a x b) mod n
 * @todo       모듈러 값과 오버플로우 상황을 고려하여 작성한다.
 */
uint ModMul(uint x, uint y, uint n) {
    uint result;

    result = my_mul(x, y, n);

    return result;
}

/*
 * @brief      모듈러 거듭제곱 연산을 하는 함수.
 * @param      uint base   : 피연산자1.
 * @param      uint exp    : 피연산자2.
 * @param      uint n      : 모듈러 값.
 * @return     uint result : 피연산자의 연산에 대한 모듈러 연산 값. (base ^ exp) mod n
 * @todo       모듈러 값과 오버플로우 상황을 고려하여 작성한다.
               'square and multiply' 알고리즘을 사용하여 작성한다.
 */
uint ModPow(uint base, uint exp, uint n) {
    uint result;

    result = my_exp(base, exp, n);
    
    return result;
}

/*
 * @brief      입력된 수가 소수인지 입력된 횟수만큼 반복하여 검증하는 함수.
 * @param      uint testNum   : 임의 생성된 홀수.
 * @param      uint repeat    : 판단함수의 반복횟수.
 * @return     uint result    : 판단 결과에 따른 TRUE, FALSE 값.
 * @todo       Miller-Rabin 소수 판별법과 같은 확률적인 방법을 사용하여,
               이론적으로 4N(99.99%) 이상 되는 값을 선택하도록 한다. 
 */
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

        tmp = ModPow(a, q, testNum);
        if (tmp == 1)
        {
            possible = TRUE;
            continue;
        }

        for (j = 0; j < k; j++)
        {
            tmp = ModPow(a, my_pow(2, j) * q, testNum);
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

/*
 * @brief       모듈러 역 값을 계산하는 함수.
 * @param       uint a      : 피연산자1.
 * @param       uint m      : 모듈러 값.
 * @return      uint result : 피연산자의 모듈러 역수 값.
 * @todo        확장 유클리드 알고리즘을 사용하여 작성하도록 한다.
 */
uint ModInv(uint a, uint m) {
    uint result;
    uint p1 = 0, p2 = 1, p3, quot = 0, rem = 0, b = m;

    while (b != 1)
    {
        quot = my_div(b, a, &rem);
        b = a;
        a = rem;
        p3 = ModAdd(p1, ModMul(p2, quot, m), '-',m);
        p1 = p2;
        p2 = p3;
    }
    result = p3;

    return result;
}

/*
 * @brief     RSA 키를 생성하는 함수.
 * @param     uint *p   : 소수 p.
 * @param     uint *q   : 소수 q.
 * @param     uint *e   : 공개키 값.
 * @param     uint *d   : 개인키 값.
 * @param     uint *n   : 모듈러 n 값.
 * @return    void
 * @todo      과제 안내 문서의 제한사항을 참고하여 작성한다.
 */
void miniRSAKeygen(uint *p, uint *q, uint *e, uint *d, uint *n){
    double r1 = 1, r2 = 1, r3 = 1;
    bool check = TRUE;
    uint pi_n, rem, tmp1, tmp2;
    uint e_32 = my_pow(2, 32), e_16 = my_pow(2, 16), e_31 = my_pow(2, 31), e_17 = my_pow(2, 17), e_15 = my_pow(2, 15);

    // n 만들기.
start:

    // 0보다 큰 r1과 r2 만들기.
    r1 = 0;
    *p = 0;
    r1 = WELLRNG512a() * (e_32 - 1);
    r1 = my_mod(r1, e_16);
    *p = (uint)(r1 + 1);
    my_div(*p, 2, &rem);
    if (*p < e_15)
    {
        goto start;
    }
    if (rem == 0)
    {
        goto start;
    }
    if (IsPrime(*p, 10) == FALSE)
    {
        goto start;
    }
    

maker2:
    r2 = 0;
    *q = 0;
    r2 = WELLRNG512a() * (e_32 - 1);
    r2 = my_mod(r2, e_16);
    *q = (uint) (r2 + 1);
    my_div(*q, 2, &rem);
    if (*q < e_15)
    {
        goto maker2;
    }
    if (rem == 0)
    {
        goto maker2;
    }
    if (IsPrime(*q, 10) == FALSE)
    {
        goto maker2;
    }

    *n = *p * *q;
    if (!((*n < (e_32 - 1)) && (*n >= e_31)))
    {
        goto start;
    }

    // 공개키 만들기.
    tmp1 = *p;
    tmp2 = *q;
    pi_n = (tmp1 - 1) * (tmp2 - 1);
    do
    {
        r3 = 1;
        r3 = WELLRNG512a() * pi_n;
        *e = (uint) r3;
    } while((r3 < 2) || (gcd(*e, pi_n) != 1));
    // 개인키 만들기.
    *d = ModInv(*e, pi_n);
}

/*
 * @brief     RSA 암복호화를 진행하는 함수.
 * @param     uint data   : 키 값.
 * @param     uint key    : 키 값.
 * @param     uint n      : 모듈러 n 값.
 * @return    uint result : 암복호화에 결과값
 * @todo      과제 안내 문서의 제한사항을 참고하여 작성한다.
 */
uint miniRSA(uint data, uint key, uint n) {
    uint result;

    result = my_exp(data, key, n);

    return result;
}

uint gcd(uint a, uint b) {
    uint prev_a;

    while(b != 0) {
        // printf("GCD(%u, %u)\n", a, b);
        prev_a = a;
        a = b;
        while(prev_a >= b) prev_a -= b;
        b = prev_a;
    }
    // printf("GCD(%u, %u)\n\n", a, b);
    return a;
}

int main(int argc, char* argv[]) {
    byte plain_text[4] = {0x12, 0x34, 0x56, 0x78};
    uint plain_data, encrpyted_data, decrpyted_data;
    uint seed = time(NULL);

    memcpy(&plain_data, plain_text, 4);

    // 난수 생성기 시드값 설정
    seed = time(NULL);
    InitWELLRNG512a(&seed);

    // RSA 키 생성
    miniRSAKeygen(&p, &q, &e, &d, &n);
    printf("0. Key generation is Success!\n ");
    printf("p : %u\n q : %u\n e : %u\n d : %u\n N : %u\n\n", p, q, e, d, n);

    // RSA 암호화 테스트
    encrpyted_data = miniRSA(plain_data, e, n);
    printf("1. plain text : %u\n", plain_data);    
    printf("2. encrypted plain text : %u\n\n", encrpyted_data);

    // RSA 복호화 테스트
    decrpyted_data = miniRSA(encrpyted_data, d, n);
    printf("3. cipher text : %u\n", encrpyted_data);
    printf("4. Decrypted plain text : %u\n\n", decrpyted_data);

    // 결과 출력
    printf("RSA Decryption: %s\n", (decrpyted_data == plain_data) ? "SUCCESS!" : "FAILURE!");

    return 0;
}