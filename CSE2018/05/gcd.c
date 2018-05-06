#include <stdio.h>

int gcd(int a, int b) {
	while (a != b) {
		if (a > b)
			a -= b;
		else
			b -= a;
	}
	
	return a;
}

int main(void) { 
	printf("gcd(10, 15) = %d\n", gcd(10, 15));
	printf("gcd(125, 13) = %d\n", gcd(125,13));
	printf("gcd(625, 225) = %d\n", gcd(625, 225));
	printf("gcd(6840, 324) = %d\n", gcd(6840, 324));

	return 0;
}
