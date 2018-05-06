#include "client.h"
#include "mq.h"

int main() {
    key_t key_id;
    int server_id;
    msgbuf mybuf;
    FILE *f;
    char* command;

    f = fopen("data/server_id.txt", "r");
    fscanf(f, "%d\n", &server_id);
    fclose(f);
    key_id = msgget(server_id, IPC_CREAT|0666);
    if (key_id < 0) {
        perror("msgget error : ");
        exit(0);
    }
    printf("The Key-Value Storage.\n");
    printf("Student ID: 2015038568\n");
    printf("Name: Jang Hoyeon\n");
    printf("Team number: 10\n");
    printf( "----------------------------------------------------------------------\n");
    printf("Please ENTER or type command to continue.\n");
    printf("(L = command list)\n");
    while(1) {
        printf("[command]>> ");
        fgets(command, sizeof(command), stdin);
        while (strlen(command) != 2) {
            printf("Invalid Input!\n");
            printf("[command]>> ");
            fgets(command, sizeof(command), stdin);
        }
        switch (command[0]) {
            case 'q': case 'Q': printf("Bye Bye!\n"); return 0;
            case 'g': case 'G': ui_get(key_id); break;
            case 'p': case 'P': ui_put(key_id); break;
            case 'd': case 'D': ui_delete(key_id); break;
            case 'l': case 'L': printf("\nG: Get Data\nP: Put Data\nD: Delete Data\nQ: Quit\n\n"); break;
            default: printf("Invalid command!\n");
        }
    }

    return 0;
}
