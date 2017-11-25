#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define BSIZE 1024

int main(int argc, char const *argv[]) {
    if (argc < 2) {
        printf("Not enough input\n");
        exit(0);
    }

    int src, dest, n, c;
    char buf[BSIZE];

    // Source file Open
    {
        // open file
        if ((src = open(argv[1], O_RDONLY)) == -1) {
            perror("ERROR in open at src: ");
            exit(1);
        }

        // file pointer reset
        if ((c = lseek(src, 0, SEEK_SET)) == -1) {
            close(src);
            perror("ERROR in lseek at src: ");
            exit(1);
        }
    }

    // Destination file Open
    {
        // open file
        if ((dest = open(argv[2], O_CREAT | O_WRONLY)) == -1) {
            close(src);
            perror("ERROR in open at dest: ");
            exit(1);
        }

        // file pointer reset
        if ((c = lseek(dest, 0, SEEK_SET)) == -1) {
            close(src);
            close(dest);
            perror("ERROR in lseek at dest: ");
            exit(1);
        }
    }

    // File copy
    while ((n = read(src, buf, BSIZE)) >= 0) {
        if (n == 0) {
            break;
        }

        write(dest, buf, n);
    }


    close(src);
    close(dest);

    return 0;
}
