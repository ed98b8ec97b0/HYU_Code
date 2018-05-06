#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "smsh.h"

void cd(int argc, char* argv[]) {
    int n;

    if (argc == 1) {
        chdir(getenv("HOME"));
    } else if (argc == 2) {
        n = chdir(argv[1]);
        if (n < 0) {
            printf("cd: no such file or directory :%s\n", argv[1]);
        }
    } else {
        printf("cd: string not in pwd: %s\n", argv[1]);
    }
}
