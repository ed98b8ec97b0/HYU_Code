#include "proxy.h"

int main(int argc, char const *argv[])
{
	char* address;

	if (argc < 2) {
        fprintf(stderr,"ERROR, no port provided\n");
        exit(1);
				printf("%s\n", address);

    }

    address = get_address(argv[1]);
 	get_response(argv[1]);

	return 0;
}
