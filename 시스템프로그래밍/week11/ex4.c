#include <stdio.h>
#include <stdlib.h>

extern char **environ;

int main(int argc, char const *argv[], char* envp[])
{
	int i;

	for (i = 0; argv[i] != NULL; i++) {
		printf("argv[%d] = %s\n", i, argv[i]);
	}
	printf("\n");

	for (i = 0; envp[i] != NULL; i++) {
		printf("envp[%d] = %s\n", i, envp[i]);
	}
	printf("\n");

	for (i = 0; environ[i] != NULL; i++) {
		printf("environ[%d] = %s\n", i, environ[i]);
	}
	printf("\n");

	printf("HOME = %s\n", getenv("HOME"));
	putenv("HOME=/home/CSE4009/TA3/lab8");
	printf("HOME = %s\n", getenv("HOME"));

	return 0;
}