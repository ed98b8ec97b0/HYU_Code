#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main(void)
{
	pid_t pid;
	if ((pid = fork()) < 0) {
		perror("fork error\n");
		exit(1);
	} else if (pid == 0) {
		if (execl("/home/CSE4009/TA3/lab8/echoall", "echoall", "myargl", "MYAGR2", (char*)0) < 0) {
			fprintf(stderr, "execl error\n");
			exit(1);
		}
	}

	if (waitpid(pid, NULL, 0) < 0) {
		perror("wait error\n");
		exit(1);
	} else if (pid > 0) {
		printf("\n\n--------------------------------------------\n\n");
		if (execlp("echoall", "echoall", "only 1 arg", (char*)0) < 0) {
			fprintf(stderr, "execlp error\n");
			exit(1);
		}
	}


	exit(0);
}