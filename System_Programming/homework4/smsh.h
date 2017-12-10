#define CMD_SIZE 1024
#define PATH_SIZE 512
#define MAX_ARG 32

typedef struct _command {
    int argc;
    char *argv[MAX_ARG];
} command;

int main(void);
void cd(int argc, char* argv[]);
command* parse_cmd(char *cmd);
