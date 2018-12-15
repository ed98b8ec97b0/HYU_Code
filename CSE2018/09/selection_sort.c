/* 
      [1] Pass the Argument, when executing the main executable

	./a.out 444 5 -900 03 000 2 93 4 -123 5 1 3 2

	not to compile several times, using main arguments or using scanf is better.

      [2] Use atoi()

	int atoi(char const *str);
	in 'stdlib.h'
	changes String to Integer

      [3] Selection sort algorithm
	
*/

#include <stdio.h>
#include <stdlib.h>

void selection_sort(int *, int);
void swap(int *, int *);
void print_array(int *, int);

int main(int argc, char const* argv[]){	
	int a[argc-1];
	for (int i = 1; i < argc; i++){
		a[i-1] = atoi(argv[i]);
	}
	int n = sizeof(a)/sizeof(int);

	selection_sort(a,n);

	printf("\n   After Selection Sort :\n\n");
	for(int j = 0; j < n; ++j)
		printf("%5d",a[j]);
	printf("\n\n");
	return 0;
}

void selection_sort(int a[], int n)
{
	int* b;
	b = (int*) malloc(sizeof(int) * n);
	int i, j, rank, repeat;

	for (i = 0; i < n; i++) 
	{
		b[i] = 0;
	}
	for (i = 0; i < n; i++) 
	{
		rank = 0;
		repeat = 0;
		for (j = 0; j < n; j++) 
		{
			if (a[i] > a[j]) 
			{
				rank++;
			}
			if (a[i] == a[j]) 
			{
				repeat++;
			}
		}
		if (a[i] != b[rank]) 
		{
			for (j = 0; j < repeat; j++) 
			{
				b[rank + j] = a[i];
			}		
		}
		print_array(b, n);
	}
	for (i = 0; i < n; i++) 
	{
		swap((a + i), (b + i));
	}
}

void swap(int *a, int *b){
	int tmp = *a;
	*a = *b;
	*b = tmp;
}

void print_array(int *a, int n){
	for(int i = 0; i < n; ++i)
		printf("%5d", a[i]);
	putchar('\n');
}