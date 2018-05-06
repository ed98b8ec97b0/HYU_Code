#include <stdio.h>

int main(void) {
	int k, result = 0;

	printf("Enter the level of times table : ");
	scanf("%d", &k);
	for (int i = 1; i <= 9; i++) {
		result += k;
		printf("%d * %d = %d\n", k, i, result);
	}

	return 0;
}
