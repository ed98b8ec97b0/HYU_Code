#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

void myalarm() {
    printf("ding dong dong\n");
}

void newalarm(int secs) {
    pid_t pid, ppid;

    pid = fork();
    if (pid == -1) {
        perror("fork error");
        exit(1);
    }
    else if (pid == 0) {
        ppid = getppid();
        sleep(secs);
        kill(ppid, SIGALRM);
        exit(0);
    }
}

int main(void) {
    int i = 0;

    printf("alarm setting\n");
    signal(SIGALRM, myalarm);

    newalarm(1);
    while(i < 5) {
        printf("ok\n");
        pause();
        newalarm(2);
        i++;
    }

    return 0;
}
