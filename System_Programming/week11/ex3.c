#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(void)
{
	int pid;

	pid = fork();
	if (pid == 0) {
		sleep(2);
		printf("My pid(Child): %d\n", getpid());
		printf("My Parent pid: %d\n", getppid());
	} else if (pid > 0) {
		printf("MY pid(Parent): %d\n", getpid());
		sleep(5);
	} else {
		perror("fork error: ");
		exit(0);
	}

	return 1;
}