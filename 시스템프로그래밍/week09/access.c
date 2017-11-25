#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
    int n;

    n = access(argv[1], F_OK);
    if (n != 0)
        perror("File not exist");
    else
        printf("File exist\n");

    return 0;
}