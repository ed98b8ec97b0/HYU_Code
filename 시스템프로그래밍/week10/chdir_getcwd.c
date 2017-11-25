#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
    char buf[255];
    int n;

    getcwd(buf, 255);
    printf("이전 작업 디렉토리: %s\n", buf);

    n = chdir(argv[1]);
    if (n) {
        perror("error");
        exit(-1);
    }

    getcwd(buf, 255);
    printf("현재 작업 디렉토리 %s\n", buf);
    
    return 0;
}