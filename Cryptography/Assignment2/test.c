#include "miniRSA.h"
#include "my_code.h"
#include <stdio.h>
#include <time.h>
#include <math.h>
#include <stdlib.h>


int main(void)
{
    uint seed = time(NULL);
    seed = time(NULL);
    InitWELLRNG512a(&seed);
    int i;
    uint test = 2, test2, rem = 0;
    bool result;
    double a, b;

    my_exp(2018915346, 1996532551, 3104153971);

    return 0;
}