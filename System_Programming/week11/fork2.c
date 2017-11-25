#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

int glob = 6;

int main(int argc, char const *argv[])
{
	int var = 88;
	pid_t pid;

	printf("before fork:\t");
	printf("pid = %d, glob = %d, var = %d\n", getpid(), glob, var);
	if ((pid = fork()) < 0) {
		perror("fork error");
		exit(1);
	} else if (pid == 0) {
		glob++;
		var++;
		exit(1);
	} else {
		sleep(2);
	}

	printf("after fork:\t");
	printf("pid = %d, glob = %d, var = %d\n", getpid(), glob, var);

	return 0;
}