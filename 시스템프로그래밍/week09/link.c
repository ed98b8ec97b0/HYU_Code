#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv)
{
    int n;

    if (argc != 4) {
        printf("Usage: ./link [original file] [copy file (hard)] [copy file(symbolic)]");
    }
    
    n = access(argv[1], F_OK);
    if (n != 0) {
        printf("original file not exists\n");
        return -1;
    }
    n = access(argv[2], F_OK);
    if (n == 0) {
        printf("file already exists\n");
        return -1;
    }
    n = access(argv[3], F_OK);
    if (n == 0) {
        printf("file already exists\n");
        return -1;
    }

    n = link(argv[1], argv[2]);
    if (n == -1)
        perror("hard link error");
    n = symlink(argv[1], argv[3]);
    if (n == -1)
        perror("symbolic link error");

    return 0;
}