#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

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

int main(void) {
    char path[PATH_SIZE], cmd[CMD_SIZE];

    // 구동시 등장하는 쉘 인트로 문구.
    printf("================================================================================\n");
    printf("                               Simple Linux Shell\n");
    printf("                               2015038568 장호연\n");
    printf("                               시스템프로그래밍\n");
    printf("================================================================================\n\n");

    // 명령어 받아오기.
    while(1) {
        // path 가져오기.
        if (getcwd(path, PATH_SIZE) < 0) {
            perror("getcwd(): ");
            exit(1);
        }

        printf("%s$ ", path);
        fgets(cmd, CMD_SIZE, stdin);

        command *parsed = parse_cmd(cmd);

        // parse_cmd test
        // printf("argc = %d\n", parsed->argc);
        // for (int i = 0; i < parsed->argc; i++) {
        //     printf("argv[%d] = %s\n", i, parsed->argv[i]);
        // }

        // parse_cmd->argv[] test

        if ((strlen(parsed->argv[0]) <= 5) && (strncmp(parsed->argv[0], "exit", 4) == 0)) {
            printf("BYE!\n");
            exit(0);
        } else if ((strlen(parsed->argv[0]) <= 3) && (strncmp(parsed->argv[0], "cd", 2) == 0)) {
            cd(parsed->argc, parsed->argv);
        } else {
            printf("smsh: command not found: %s\n", parsed->argv[0]);
        }
    }

    return 0;
}
