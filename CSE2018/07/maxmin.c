#include <stdio.h>

static int maxcount = 0, mincount = 0;

int max(int a) {
	static int maxval;

	if (maxcount == 0 || maxval < a) {
		maxval = a;
	}
	maxcount++;

	return maxval;
}

int min(int a) {
	static int minval;

	if (mincount == 0 || minval > a) {
		minval = a;
	}
	mincount++;

	return minval;
}

int main(int argc, char const *argv[]) {
	int i;

	for (i = 0; i < 15; i++) {
		max(i);
	}
	for (i = 20; i > 1; i--) {
		min(i);
	}
	printf("max() called %d times, maxval : %d\n", maxcount, max(5));
	printf("min() called %d times, minval : %d\n", mincount, min(5));

	return 0;
}
