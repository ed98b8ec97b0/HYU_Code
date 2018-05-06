#include "server.h"
#include "mq.h"

int main() {
    key_t key_id;
    int server_id;
    msgbuf mybuf;
    FILE *f;
    srand(time(NULL));

    f = fopen("data/server_id.txt", "r");
    fscanf(f, "%d", &server_id);
    fclose(f);
    key_id = msgget(server_id, IPC_CREAT|0666);
    msgctl(key_id, IPC_RMID, NULL);
    server_id = rand() % 1000000;
    f = fopen("data/server_id.txt", "w");
    fprintf(f, "%d\n", server_id);
    fclose(f);
    key_id = msgget((key_t) server_id, IPC_CREAT|0666);
    if (key_id < 0) {
        perror("msgget error : ");
        exit(0);
    }


    while(1) {
        if(msgrcv(key_id, (void *) &mybuf, BUFSIZE, TOSERVER, 0) == -1) {
            perror("msgrcv error : ");
            exit(0);
        }
        switch(mybuf.command) {
            case 'd': delete(mybuf.key); break;
            case 'g': get(key_id, mybuf.key); break;
            case 'p': put(mybuf.key, mybuf.value); break;
        }
    }

    return 0;
}
