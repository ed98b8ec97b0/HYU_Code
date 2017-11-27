#include "proxy.h"

int main(int argc, char const *argv[])
{
	item req_item, rec_item;

	if (argc < 2) {
        fprintf(STDERR_FILENO,"ERROR, no port provided\n");
        exit(1);
    }

    req_item = proxy_req(argv[1]);
    rec_item = proxy_rec(req_item);

	return 0;
}
