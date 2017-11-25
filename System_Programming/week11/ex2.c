#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(void)
{
	int pid;
	int status;

	pid = fork();
	if (pid < 0) {
		perror("fork error: ");
		exit(1);
	}
	if (pid == 0) {
		printf("I'm Child\n");
		sleep(5);
		exit(123);
	} else {
		printf("Parent: waiting for child(%d)\n", pid);
		waitpid(pid, &status, 0);
		if (WIFEXITED(status)) {
			printf("Exit OK.\n");
			printf("Return Value: %d\n", WEXITSTATUS(status));
		} else if (WIFSIGNALED(status)) {
			printf("Signal OK.\n");
			printf("Signal Number: %d\n", WTERMSIG(status));
		}
	}

	return 0;
}