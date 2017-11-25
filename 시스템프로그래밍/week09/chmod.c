#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>

int main(int argc, char **argv)
{
    int n;

    n = chmod(argv[1], S_IRUSR | S_IWUSR);
    if (n < 0) {
        perror("chmod error");
        exit(0);
    }
}