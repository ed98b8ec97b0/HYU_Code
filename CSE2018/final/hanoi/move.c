#include "hanoi.h"

void move(int n, char a, char b, char c) {
    if (n == 1) {
        ++cnt;
        printf("%5d: %s%d%s%c%s%c.\n", cnt,
            "Move disk ", 1, " from tower ", a, " to tower ", c);
    }
    else {
        move(n-1, a, c, b);
        ++cnt;
        printf("%5d: %s%d%s%c%s%c.\n", cnt,
            "Move disk ", n, " from tower ", a, " to tower ", c);
        move(n-1, b, a ,c);
    }
}

/*
n = 3 일 때 실행 추적.
call move(3, A, B, C)
call move(2, A, C, B)
call move(1, A, B, C)
Move disk 1 from tower A to C
Move disk 2 from tower A to B
call move(1, C, A, B)
Move disk 1 from tower C to B
Move disk 3 from tower A to C
call move(2, B, A, C)
call move(1, B, C, A)
Move disk 1 from tower B to A
Move disk 2 from tower B to C
call move(1, A, B, C)
Move disk 1 from tower A to C
*/