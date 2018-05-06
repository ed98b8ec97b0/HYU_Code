#include <stdio.h>

int main(void){
	char a[4][10] = {"HaHa", "han yang ", "cheese", " Iphone"}; 
	char *(pc[4]) = {a[0], a[1], a[2], a[3]};
	char **ppc = pc;

    printf("%s%c%s%c%s\n", *(ppc) + 2, *(*(pc + 1) + 2), *(ppc + 1) + 4, *(*(pc + 2)), *(ppc + 2) + 4);

	return 0;
}

// Print "Hanyang cse"
