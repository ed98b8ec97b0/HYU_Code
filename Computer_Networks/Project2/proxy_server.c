#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <netdb.h> 

#define BACKLOG 5
#define BUFF_SIZE 512
#define CASH_SIZE 10
#define NOT_FOUND "HTTP/1.1 404 NOT Found\r\n\r\n"
#define URL_SIZE 300
#define PATH_SIZE 300

void error(char *msg)
{
    perror(msg);
    exit(1);
}

int main(int argc, char* argv[])
{
    int proxy_sock, cli_sock, serv_sock;
    int port_no;
    int n, m, flag;
    char buffer[BUFF_SIZE], url[URL_SIZE], path[PATH_SIZE];
    char token1[URL_SIZE], token2[URL_SIZE], token3[10];
    char* temp = NULL;
    struct sockaddr_in proxy_addr, serv_addr, cli_addr;
    struct hostent *server;
    socklen_t clilen;

    if (argc < 2)
    {
        fprintf(stderr, "ERROR argument");
        exit(1);
    }

    printf("Proxy server\n");
    printf("Computer Network Project2\n");
    printf("Hanyang University ERICA Campus\n");
    printf("2015038568 장호연\n\n");

    // proxy server socket open
    proxy_sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (proxy_sock < 0)
    {
        error("ERROR proxy socket");
    }
    

    // reset proxy_addr
    memset((char *) &proxy_addr, 0, sizeof(proxy_addr));
    port_no = atoi(argv[1]);
    proxy_addr.sin_family = AF_INET;
    proxy_addr.sin_addr.s_addr = INADDR_ANY;
    proxy_addr.sin_port = htons(port_no);

    int opt = 1;
    setsockopt(proxy_sock, SOL_SOCKET, SO_REUSEADDR, (const char *)&opt, sizeof(int));

    // binding proxy socket
    n = bind(proxy_sock, (struct sockaddr *) & proxy_addr, sizeof(proxy_addr));
    if (n < 0)
    {
        error("ERROR bind proxy socket");
    }

    // proxy socket into listening mode 
    n = listen(proxy_sock, BACKLOG);
    if (n < 0)
    {
        error("ERROR listen proxy socket");
    }

    while(1)
    {
        clilen = sizeof(cli_addr);

        // accepting client socket
        cli_sock = accept(proxy_sock, (struct sockaddr *) &proxy_addr, &clilen);
        if (cli_sock < 0)
        {
            error("ERROR accept client socket");
        }

        // reset buffer
        memset(buffer, 0, BUFF_SIZE);

        // read client socket
        n = read(cli_sock, buffer, BUFF_SIZE-1);
        if (n < 0)
        {
            error("ERROR read client socket");
        }

        // split first headline
        n = 0;
        sscanf(buffer, "%s %s %s", token1, token2, token3);
        strcpy(token1, token2);
        for (int i = 7; i < strlen(token2); i++)
        {
            if (token2[i] == ':')
            {
                n = 1;
                break;
            }
        }

        // split URL + path and port
        temp = strtok(token2, "//"); // temp = http://
        if (n == 0)
        {
            port_no = 80;
            temp = strtok(NULL, "/"); // temp = URL + path
        }
        else
        {
            temp = strtok(NULL, ":"); // temp = URL + path
        }

        if (n == 1)
        {
            temp = strtok(NULL, "/");
            port_no = atoi(temp);
        }
        sprintf(url, "%s", temp);
        
        // connet to server
        server = gethostbyname(url);
        if (server == NULL)
        {
            n = write(cli_sock, NOT_FOUND, 27);
            if (n < 0)
            {
                error("ERROR write client socket");
            }
            error("ERROR gethostbyname");
        }

        // get path
        strcat(token1, "^]");
        temp = strtok(token1, "//");
        temp = strtok(NULL, "/");
        if (temp != NULL)
        {
            temp = strtok(NULL, "^]");
        }
        sprintf(path, "%s", temp);

        flag = check(url, path);
        if (flag == 1)
        {
            object* obje = hit(url, path);

            for (int i = 0; i < obje->count; i++)
            {
                n = send(cli_sock, obje->buffer, obje->length, 0);
                if (n < 0)
                {
                    error("ERROR write client socket");
                }
            }
        }
        else
        {
            memset((char *) &serv_addr, 0, sizeof(serv_addr));
            serv_addr.sin_family = AF_INET;
            memcpy((char *) &serv_addr.sin_addr.s_addr, (char *) server->h_addr, server->h_length);
            serv_addr.sin_port = htons(port_no);

            // proxy server socket open
            serv_sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
            if (serv_sock < 0)
            {
                error("ERROR proxy socket");
            }

            n = connect(serv_sock, (struct sockaddr *) &serv_addr, sizeof(serv_addr));
            if (n < 0)
            {
                error("ERROR connect server");
            }

            // write buffer to using receive http response from server.
            memset(buffer, 0, BUFF_SIZE);
            if (temp != NULL)
            {
                sprintf(buffer, "GET /%s %s\r\nHost: %s\r\nConnection: close\r\n\r\n", path, token3, url);
            }
            else
            {
                sprintf(buffer, "GET / %s\r\nHost: %s\r\nConnection: close\r\n\r\n", token3, url);
            }
            
            n = write(serv_sock, buffer, BUFF_SIZE);
            if (n < 0)
            {
                error("ERROR write proxy");
            }
            
            memset(buffer, 0, BUFF_SIZE);

            // cache first node;
            flag = 0;
            object* front = (object*) malloc(sizeof(object));
            front->url = url;
            front->path = path;
            front->count = 1;
            front->next = NULL;
            object* header = front;
            // send request and receive response.
            do
            {   
                n = recv(serv_sock, buffer, BUFF_SIZE, 0);
                if (n < 0)
                {
                    error("ERROR read proxy");
                }
                if (flag == 0)
                {
                    front->length = n;
                    front->buffer = buffer;
                }
                else
                {
                    object* temp = (object*) malloc(sizeof(object));
                    temp->url = url;
                    temp->path = path;
                    temp->length = n;
                    front->count++;
                    header->next = temp;
                    header = header->next;
                }
                

                if (n > 0)
                {
                    m = send(cli_sock, buffer, n, 0);
                    if (m < 0)
                    {
                        error("ERROR write client socket");
                    }
                }

                flag = 1;
            } while (n > 0);

            if (count <= 10)
            {
                miss(first);
            }
        }

        
        
        memset(buffer, 0, BUFF_SIZE);
        close(serv_sock);
        close(cli_sock);
    }
    close(proxy_sock);

    return 0;
}