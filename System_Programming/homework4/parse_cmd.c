#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "smsh.h"

command* parse_cmd(char *cmd) {
    char *tmp = NULL;
    command *output = (command *)malloc(sizeof(command));
    output->argc = 0;
    output->bg_flag = 0;

    if (cmd[strlen(cmd) - 2] == '&') {
        output->bg_flag = 1;
        cmd[strlen(cmd) - 2] = '\0';
    }

    tmp = strtok(cmd, " \n");
    while (tmp != NULL) {
        output->argv[output->argc] = tmp;
        output->argc++;
        tmp = strtok(NULL, " \n");
    }

    if (output->argc == 1) {
        tmp = strtok(output->argv[0], "\0");
        output->argv[0] = tmp;
    }

    return output;
}
