#include <stdio.h>
#include <stdlib.h>

int main()
{
	int fd = 3;
	long pos;

	pos = lseek(fd, 0l, SEEK_CUR);
	printf("\tpos in openfexec(): is %ld\n", pos);

	pos = lseek(fd, 50l, SEEK_CUR);
	printf("\tNew pos after lseek() in openfexec() is %ld\n\n", pos);

	exit(pos < 0 ? !0 : 0);
}