#include "proxy.h"

char* get_address(int portno)
{
	int sockfd, newsockfd, n;
	socklen_t clilen;
	char buffer[256], *token = NULL;
	struct sockaddr_in serv_addr, cli_addr;

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0)
		error("socket");
	
	memset((char *) &serv_addr, 0, sizeof(serv_addr));
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr =  INADDR_ANY;
	serv_addr.sin_port = htons(portno);

	n = bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr));
	if (n < 0)
		error("bind");

	listen(sockfd, 5);

	clilen = sizeof(cli_addr);
	newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
	if (newsockfd < 0)
		error("accept");

	memset((char *) buffer, 256);
	n = read(newsockfd, buffer, 255);
	if (n < 0)
		error("read");
	
	token = strtok(buffer, "");
}