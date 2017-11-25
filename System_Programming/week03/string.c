#include <stdio.h>
#include <string.h>

int main() {
	char str1[20] = "System Programming";
	int lenght;
	char str2[20] = "System programming";
	char str3[20];
	char str4[20] = "System";
	char str5[20] = "Programming";
	char* char_ptr;

	printf("\n----- Ex1 -----\n");
	printf("length of '%s': %lu\n", str1, strlen(str1));
	printf("\n----- Ex2 -----\n");
	if (strcmp(str1, str2)) {
		printf("'%s' and '%s' are not equal.\n", str1, str2);
	} else {
		printf("'%s' and '%s' are equal.\n", str1, str2);
	}
	printf("\n----- Ex3 -----\n");
	strncpy(str3, str1, 6);
	printf("str3: '%s'\n", str3);
	printf("\n----- Ex4 -----\n");
	printf("str4: '%s'\n", strcat(str4, str5));
	printf("\n----- Ex5 -----\n");
	char_ptr = strchr(str1, 'P');
	printf("location 'P' in '%s' is %ld\n", str1, char_ptr-str1);

	return 0;
}
