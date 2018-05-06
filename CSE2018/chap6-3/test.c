#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[]) {
    int a[3][3][3] = {
        {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}},
        {{9, 10, 11}, {12, 13, 14}, {15, 16, 17}},
        {{18 , 19, 20}, {21, 22, 23}, {24, 25, 26}}
    }, (*p1)[3][3], *(*p2)[3], ***p3;;

    p3[0] = ((b[i])[0][0]);

    printf("*a = %p\n", *a);
    printf("&a[0] = %p\n", *p3);

    return 0;
}