#include "client.h"
#include "mq.h"

void ui_get(key_t key_id) {
    char* command;
    int first, last, msgr;
    char* value = NULL;
    msgbuf mybuf;

    printf("Please enter the first number of the key range what you want to search.\n");
    printf("[0 - 2^32] >> ");
    scanf("%u", &first);
    getchar();
    while (first > MAX) {
        printf("Invalid input!\n");
        printf("Please re-enter the first number of the key range what you want to search.\n");
        printf("[0 - 2^32] >> ");
        scanf("%u", &first);
        getchar();
    }
    printf("Please enter the last number of the key range what you want to search.\n");
    printf("[%d - 2^32] >> ", first);
    scanf("%u", &last);
    getchar();
    while (last < first || last > MAX) {
        printf("Invalid Input!\n");
        printf("Please re-enter the last number of the key range what you want to search.\n");
        printf("[%d - 2^32] >> ", first);
        scanf("%u", &last);
        getchar();
    }
    for (unsigned int i = first; i <= last; i++) {
        mybuf.mtype = TOSERVER;
        mybuf.command = 'g';
        mybuf.key = i;

        msgr = msgsnd(key_id, (void *) &mybuf, BUFSIZE, IPC_NOWAIT);
        if (msgr == -1) {
            perror("msgsnd error : ");
            exit(0);
        }
        msgr = msgrcv(key_id, (void *) &mybuf, BUFSIZE, TOCLIENT, 0);
        if (msgr == -1) {
            perror("msgrcv error : ");
            exit(0);
        }
        printf("(%d, %s)\n", i, mybuf.value);
    }

    return;
}
