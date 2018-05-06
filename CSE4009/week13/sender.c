#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <errno.h>
#include <string.h>

#define MSGSIZE 80

char *fifo = "fifo";

int main(int argc, char* argv[]) {
    int fd, i, nwrite;
    char msgbuf[MSGSIZE];

    if (argc < 2) {
        fprintf(stderr, "Usage: ./sender msg...\n");
        exit(1);
    }

    if ((fd = open(fifo, O_WRONLY | O_NONBLOCK)) < 0) {
        perror("fifo open failed");
    }

    for (i = 1; i < argc; i++) {
        if (strlen(argv[i]) > MSGSIZE) {
            fprintf(stderr, "message is too long: %s\n", argv[i]);
            continue;
        }
        strcpy(msgbuf, argv[i]);
        if ((nwrite = write(fd, msgbuf, MSGSIZE)) == -1) {
            perror("message write failed");
        }
    }

    return 0;
}