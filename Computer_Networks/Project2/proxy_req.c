#include "proxy.h"

item proxy_req(int portno) {
	int sockfd, newsockfd, n;
	socklen_t clilen;
	char buffer[BUFF_SIZE], *token = NULL;
	struct sockaddr_in serv_addr, cli_addr;
    item req_item;

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0)
		error("socket");
	
	memset((char *) &serv_addr, 0, sizeof(serv_addr));
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons(portno);

	n = bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr));
	if (n < 0)
		error("bind");

	listen(sockfd, 5);

	clilen = sizeof(cli_addr);
	newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
	if (newsockfd < 0)
		error("accept");

	memset((char *) buffer, 0, BUFF_SIZE);
	n = read(newsockfd, buffer, BUFF_SIZE - 1);
	if (n < 0)
		error("read");

    req_item.req = strtok(buffer, " ");
    req_item.url = strtok(NULL, " ");
    req_item.ver = strtok(NULL, " ");
    req_item.ip_addr = cli_addr.sin_addr.s_addr;
    req_item.port_num = cli_addr.sin_port;

    return req_item;
}