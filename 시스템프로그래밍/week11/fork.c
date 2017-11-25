#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char const *argv[])
{
	pid_t pid = fork();
	if (pid > 0) {
		printf("Parent Process %d: %d\n", getpid(), pid);
	} else if (pid == 0) {
		printf("Child Process %d\n", getpid());
	} else if (pid < 0) {
		perror("fork error");
		exit(0);
	}

	return 0;
}