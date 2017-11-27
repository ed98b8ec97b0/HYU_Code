#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <errno.h>

#define MSGSIZE 80

char *fifo = "fifo";

int main(int argc, char* argv[]) {
    int fd;
    char msgbuf[MSGSIZE];

    if (mkfifo(fifo, 0666) == -1) {
        if (errno != EEXIST) {
            perror("receiver: mkfifo");
        }
    }

    if ((fd = open(fifo, O_RDWR)) < 0) {
        perror("fifo open failed");
    }

    for (;;) {
        if (read(fd, msgbuf, MSGSIZE) < 0) {
            perror("message read failed");
        }
        printf("message received: %s\n", msgbuf);
    }
}