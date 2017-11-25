#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
    int n;

    if (argc != 2) {
        fprintf(stderr, "Usage: ./mkdir [Directory Name]\n");
        exit(-1);
    }

    n = mkdir(argv[1], 0755);
    if (n) {
        perror("mkdir error");
        exit(-1);
    }

    return 0;
}