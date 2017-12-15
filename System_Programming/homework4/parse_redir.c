#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "smsh.h"

#define REDIR_LIMIT 10

command* parse_redir(char* cmd) {
    command* output;
    char *cmd_line;
    int i, j = 0, stdout_newfile = 0, stdin_file = 0, stdout_append = 0, stdout_override = 0;
    int stdout_newfile_index = -1, stdin_file_index = -1, stdout_append_index = -1, stdout_override_index = -1;
    char* stdout_newfile_list[REDIR_LIMIT];
    char* stdin_file_list[REDIR_LIMIT];
    char* stdout_append_list[REDIR_LIMIT];
    char* stdout_override_list[REDIR_LIMIT];

    // TODO: 가장 빠른 기호 찾기
    for (i = 0; i < strlen(cmd); i++) {
        if (cmd[i] == '<' || cmd[i] == '>') {
            break;
        }
    }

    // TODO: 명령어 부분과 분리
    cmd_line = (char *)malloc(sizeof(char) * i);
    memmove(cmd_line, cmd, (i-1));

    // TODO: 명령어 부분 parsing
    output = parse_cmd(cmd_line);

    // TODO: redir에 따른 파일 열기
    for (; i < strlen(cmd); i++) {
        if (cmd[i] == '>') {
            if (cmd[i + 1] == '>') {
                j = 0;
                stdout_newfile = 0;
                stdin_file = 0;
                stdout_append = 1;
                stdout_override = 0;
                stdout_append_index++;
            }
            else if (cmd[i + 1] == '!') {
                j = 0;
                stdout_newfile = 0;
                stdin_file = 0;
                stdout_append = 0;
                stdout_override = 1;
                stdout_override_index++;
            }
            else {
                j = 0;
                stdout_newfile = 1;
                stdin_file = 0;
                stdout_append = 0;
                stdout_override = 0;
                stdout_newfile_index++;
            }
        }
        else if (cmd[i] == '<') {
            if (cmd[i + 1] == '<') {
                printf("usage: command < file...\n");
                j = 0;
                stdout_newfile = 0;
                stdin_file = 0;
                stdout_append = 0;
                stdout_override = 0;
            }
            else {
                j = 0;
                stdout_newfile = 0;
                stdin_file = 1;
                stdout_append = 0;
                stdout_override = 0;
                stdin_file_index++;
            }
        }
        else {
            if (stdout_newfile == 1) {
                stdout_newfile_list[stdout_newfile_index][j++] = cmd[i];
            }
            else if (stdin_file == 1) {
                stdin_file_list[stdin_file_index][j++] = cmd[i];
            }
            else if (stdout_append == 1) {
                stdout_append_list[stdout_append_index][j++] = cmd[i];
            }
            else if (stdout_override == 1) {
                stdout_override_list[stdout_override_index][j++] = cmd[i];
            }
        }
    }

    // TODO: dup2 사용해 input output 전환
    // index까지 돌면서 있는건 모두 해당되는 형태로 열어서 stdin, stdout에 덮어씌우기.


    // INDEX TEST SECTION
    printf("stdout_newfile_index = %d\n", stdout_newfile_index);
    if (stdout_newfile_index > -1)
        printf("stdout_newfile = %s\n\n", stdout_newfile_list[0]);

    printf("stdin_file_index = %d\n", stdin_file_index);
    if (stdin_file_index > -1)
        printf("stdin_file = %s\n\n", stdin_file_list[0]);

    printf("stdout_append_index = %d\n", stdout_append_index);
    if (stdout_append_index > -1)
        printf("stdout_append = %s\n\n", stdout_append_list[0]);

    printf("stdout_override_index = %d\n", stdout_override_index);
    if (stdout_override_index > -1)
        printf("stdout_override = %s\n\n", stdout_override_list[0]);

    return output;
}
