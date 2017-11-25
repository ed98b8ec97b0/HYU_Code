#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(void)
{
	int pid;
	int status;
	int cpid;

	pid = fork();
	if (pid == 0) {
		sleep(5);
		printf("I will be back %d\n", getpid());
		exit(1);
	} else if (pid > 0) {
		printf("I'm parent %d\n", getpid());
		printf("Press any key and wait\n");
		getchar();

		cpid = wait(&status);
		printf("Success: Waiting for child process\n");
		printf("PID: %d\n", cpid);
		printf("ExitValue: %d\n", WEXITSTATUS(status));
		printf("ExitStat: %d\n", WIFEXITED(status));
	} else {
		perror("fork error: ");
	}

	return 0;
}