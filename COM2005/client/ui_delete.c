#include "client.h"
#include "mq.h"

void ui_delete(key_t key_id) {
    char* command;
    int first, last, msgr;
    msgbuf mybuf;

    printf("\nPlease enter the first number of the key range what you want to delete.\n");
    printf("[0 - 2^32] >> ");
    scanf("%u", &first);
    getchar();
    while (first > MAX) {
        printf("Invalid input!\n");
        printf("\nPlease re-enter the first number of the key range what you want to delete.\n");
        printf("[0 - 2^32] >> ");
        scanf("%u", &first);
        getchar();
    }
    printf("Please enter the last number of the key range what you want to delete.\n");
    printf("[%d - 2^32] >> ", first);
    scanf("%u", &last);
    getchar();
    while (last < first || last > MAX) {
        printf("Invalid Input!\n");
        printf("Please re-enter the last number of the key range what you want to delete.\n");
        printf("[%d - 2^32] >> ", first);
        scanf("%u", &last);
        getchar();
    }
    for (unsigned int i = first; i <= last; i++) {
        mybuf.mtype = TOSERVER;
        mybuf.command = 'd';
        mybuf.key = i;
        msgr = msgsnd(key_id, (void *) &mybuf, BUFSIZE, IPC_NOWAIT);
        if (msgr == -1) {
            perror("msgsnd error : ");
            exit(0);
        }
    }
    printf("Delete Complete.\n");

    return;
}
