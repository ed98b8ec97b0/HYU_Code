#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main()
{
	int fdes, pid, excode;
	long pos;

	fdes = open("openf.c", O_RDONLY);
	printf("fdes = %d\n", fdes);

	pos = lseek(fdes, 20l, SEEK_SET);
	printf("Current position before fork() is %ld\n", pos);
	if (!fork()) {
		pos = lseek(fdes, 40l, SEEK_SET);
		printf("Current position in child after fork() is %ld\n", pos);
		exit(1);
	} else {
		wait((int *) 0);
		pos = lseek(fdes, 0l, SEEK_CUR);
		printf("Current position in parent after fork() is %ld\n\n", pos);
	}

	if (!fork()) {
		execl("./openfexec", 0);
		printf("It is an error to print this line out\n");
	}

	wait(&excode);
	pos = lseek(fdes, 0l, SEEK_CUR);
	printf("Current pos in parent after exec() is %ld\n", pos);
	printf("Exitcode of a child = %d\n\n", WEXITSTATUS(excode));

	if (pid = fork()) {
		waitpid(pid, &excode, 0);
		printf("Exitcode of a specific child = %d\n", WEXITSTATUS(excode));
		exit(0);
	}
	execl("./openfexec", 0);
	printf("It is an error to print this line out\n");
}