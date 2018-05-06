#include <stdio.h>

int main(void) {
	int size, i, j, k;
	
	printf("Christmas tree. How large? ");
	scanf("%d", &size);
	for (i = 1; i <= size; i++) {
		for (j = 1; j <= size - i; j++) {
			printf(" ");
		}
		for (k = 2 * i - 1; k > 0; k--) {
			printf("*");
		}
		printf("\n");
	}
	
	return 0;
} 