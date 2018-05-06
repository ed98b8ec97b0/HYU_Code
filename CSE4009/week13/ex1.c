#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define MSGSIZE 16

char *msg1 = "Hello, World #1";
char *msg2 = "Hello, World #2";
char *msg3 = "Hello, World #3";

int main(void) {
    char buf[MSGSIZE];
    int p[2], i;

    if (pipe(p) == -1) {
        perror("pipe call");
        exit(1);
    }

    write(p[1], msg1, MSGSIZE);
    write(p[1], msg2, MSGSIZE);
    write(p[1], msg3, MSGSIZE);

    for (i = 0; i < 3; i++) {
        read(p[0], buf, MSGSIZE);
        printf("%s\n", buf);
    }

    return 0;
}