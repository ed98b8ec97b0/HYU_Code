#include "client.h"
#include "mq.h"

void ui_put(key_t key_id) {
    unsigned int key;
    int msgr;
    char value[100];
    msgbuf mybuf;

    printf("Please enter the value what you want to put.\n");
    printf("[at most 100 letters] >> ");
    fgets(value, sizeof(value), stdin);
    value[strlen(value)-1] = '\0';
    while (strlen(value) > 100 ) {
        printf("Invalid input!\n");
        printf("Please re-enter the value what you want to put.\n");
        printf("[at most 100 letters] >> ");
        fgets(value, sizeof(value), stdin);
        value[strlen(value)-1] = '\0';
    }
    printf("Please enter the key what you want to put.\n");
    printf("[0 - 2^32] >> ");
    scanf("%u", &key);
    while (key > MAX) {
        printf("Invalid input!");
        printf("Please re-enter the key what you want to put.\n");
        printf("[0 - 2^32] >> ");
    }
    getchar();
    mybuf.mtype = TOSERVER;
    mybuf.command = 'p';
    mybuf.key = key;
    strcpy(mybuf.value, value);
    msgr = msgsnd(key_id, (void *) &mybuf, BUFSIZE, IPC_NOWAIT);
    if (msgr == -1) {
        perror("msgsnd error : ");
        exit(0);
    }

    return;
}
