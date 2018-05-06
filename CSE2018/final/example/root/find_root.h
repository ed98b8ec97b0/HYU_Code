#include <stdio.h>
#include <assert.h>

typedef double dbl;
extern int cnt;
extern const dbl eps;

dbl bisection(dbl f(dbl x), dbl a, dbl b);
dbl f(dbl x);