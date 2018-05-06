#include "server.h"
#include "mq.h"

node* hashtable[HS][HS] = {NULL};
msgbuf mybuf;

void delete(unsigned int key) {
    unsigned int hr = ktoh(key);
    unsigned int hc = ktoh(key);
    int n = 0;
    node* iter;

    if (hashtable[hr][hc] == NULL) {
        return;
    }
    if (hashtable[hr][hc]->key == key) {
        node* temp = hashtable[hr][hc];
        hashtable[hr][hc] = hashtable[hr][hc]->next;
        free(temp);
        return;
    }
    iter = hashtable[hr][hc];
    while (iter->key == key && iter->next != NULL) {
        iter = iter->next;
        n++;
    }
    if (iter->key != key) {
        return;
    }
    iter = hashtable[hr][hc];
    for (int i = 0; i < n-1; i++) {
        iter = iter->next;
    }
    node* temp = iter->next->next;
    free(iter->next);
    iter->next = temp;
}

void get(key_t key_id, unsigned int key) {
    unsigned int hr = ktoh(key);
    unsigned int hc = ktoh(key);
    char no[100] = "No data";
    int msgr;
    node* iter;

    iter = hashtable[hr][hc];
    if (iter == NULL) {
        mybuf.mtype = TOCLIENT;
        mybuf.command = '\0';
        mybuf.key = key;
        strcpy(mybuf.value, no);
        msgr = msgsnd(key_id, (void *) &mybuf, BUFSIZE, IPC_NOWAIT);
        if (msgr == -1) {
            perror("msgsnd error : ");
            exit(0);
        }
    } else {
        while (iter->key != key) {
            iter = iter->next;
        }
        mybuf.mtype = TOCLIENT;
        mybuf.command = '\0';
        mybuf.key = key;
        strcpy(mybuf.value, iter->value);
        msgr = msgsnd(key_id, (void *) &mybuf, BUFSIZE, IPC_NOWAIT);
        if (msgr == -1) {
            perror("msgsnd error : ");
            exit(0);
        }
    }
}

void put(unsigned int key, char value[100]) {
    unsigned int hr = ktoh(key);
    unsigned int hc = ktoh(key);
    node* temp = (node*) malloc (sizeof(node));;
    node* iter;

    temp->key = key;
    strcpy(temp->value, value);
    temp->next = NULL;
    if (hashtable[hr][hc] == NULL) {
        hashtable[hr][hc] = temp;
        return;
    }
    iter = hashtable[hr][hc];
    while(iter->next != NULL) {
        iter = iter->next;
    }
    iter->next = temp;
}

unsigned int ktoh(unsigned int key) {
    return key % HS;
}
