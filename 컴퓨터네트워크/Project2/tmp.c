#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <strings.h>
#include <unistd.h>
#include <fcntl.h>

#define BACKLOG 5
#define BUFF_SIZE 1024

void error(char *msg) {
    perror(msg);
    exit(1);
}

int main(int argc, char const *argv[]) {
    int server_socket, client_socket;
    int port_num;
    int n, i, j;
    int read_time;
    long file_len;
    char receive_buffer[BUFF_SIZE], send_buffer[BUFF_SIZE], *file_name, *file_type, *c_type, *content_type;
    struct sockaddr_in server_address, client_address;
    socklen_t clilen;
    FILE* source;

    if (argc < 2) {
        fprintf(stderr,"ERROR, no port provided\n");
        exit(1);
    }
    /*
    Create a socket
    PF_INET: IPv4 Protocol family
    SOCK_STREAM: TCP
    0: protocol
    */
    if ((server_socket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        error("ERROR opening socket"); // return -1, fail to create socket
    // In example, using non-standard func. bzero.
    // To keep standard, I modify bzero((char *) &serv_addr, sizeof(serv_addr)) to memset((char *) &serv_addr, 0, sizeof(serv_addr))
    memset((char *) &server_address, 0, sizeof(server_address));
    port_num = atoi(argv[1]);
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = INADDR_ANY;
    server_address.sin_port = htons(port_num);
    /*
    Bind a socket
    server_socket = socket file descriptor
    server_address = IP address and port number
    sizeof(server_address) = length of address structure
    */
    if (bind(server_socket, (struct sockaddr *) &server_address, sizeof(server_address)) < 0)
        error("ERROR on binding"); // return = -1, fail to bind a socket
    // Put a socket into passive state
    if (listen(server_socket, BACKLOG) < 0)
        error("ERROR into passive state"); // return = -1, fail to put a socket into passive state
    // Accept a new connection
    // client_address = IP address and port number of client
    while(1) {
        clilen = sizeof(client_address);
        if ((client_socket = accept(server_socket, (struct sockaddr *) &client_address, &clilen)) < 0)
            error("ERROR on accept"); // return = -1, fail to accept new connection
        memset(receive_buffer, 0x00, BUFF_SIZE);
        if ((read(client_socket, receive_buffer, BUFF_SIZE-1)) < 0)
            error("ERROR reading from socket");
        // Get file name from domain name
        file_name = strtok(receive_buffer, " ");
        file_name = strtok(NULL, " ");
        file_name += 1; // remove '/'
        source = fopen(file_name, "rb");
        if (source == NULL) { //if user input wrong file, then write 404 not found
            if ((write(client_socket, "HTTP/1.1 404 NoT Found\r\n", 24)) < 0)
                error("ERROR writing on socket");
                fclose(source);
                close(client_socket);
            continue;
        }
        // Get file length
        fseek(source, 0, SEEK_END);
        file_len = ftell(source);
        rewind(source);
        // header section
        // if file come this line, message is correctly arrived.
        if ((write(client_socket, "HTTP/1.1 200 OK]\r\n", 17)) < 0)
            error("ERROR writing on socket");
        // set Accept range
        if ((write(client_socket, "Accept-Ranges: bytes\r\n", 22)) < 0) {
            error("ERROR writing on socket");
        }
        // get content-type from file name.
        file_type = strtok(file_name, ".");
        file_type = strtok(NULL, ".");
        c_type = "text/plain";
        if (strcmp(file_type, "html") == 0)
            c_type = "text/html";
        if (strcmp(file_type, "jpg") == 0 || strcmp(file_type, "jpeg") == 0)
            c_type = "image/jpeg";
        if (strcmp(file_type, "gif") == 0)
            c_type = "image/gif";
        if (strcmp(file_type, "mp3") == 0)
            c_type = "audio/mp3";
        if (strcmp(file_type, "pdf") == 0)
            c_type = "application/pdf";
        if (strcmp(file_type, "mp4") == 0)
            c_type = "video/mp4";
        // write context-type on socket
        content_type = (char *) malloc(18 + (sizeof(char) * strlen(c_type)));
        sprintf(content_type, "Content-Type: %s\r\n", c_type);
        if ((write(client_socket, content_type, strlen(content_type))) < 0) {
            error("ERROR writing on socket");
        }
        // write context-length on socket
        i = file_len; j = 0;
        while(i != 0) {
            i /= 10;
            j++;
        }

        char content_leng[20 + j];
        sprintf(content_leng, "Content-Length: %ld\r\n\r\n", file_len);
        if ((write(client_socket, content_leng, 20 + j)) < 0)
            error("ERROR writing on socket.");
        // write data on socket
        printf("1");
        memset(send_buffer, 0x00, BUFF_SIZE);
        while ((n = fread(send_buffer, 1, BUFF_SIZE, source)) > 0) {
            if ((write(client_socket, send_buffer, n)) < 0)
                error("ERROR writing on socket");
        }
        printf("1");
        free(content_type);
        memset(send_buffer, 0x00, BUFF_SIZE);
        fclose(source);
        close(client_socket);
    }
    close(server_socket);

    return 0;
}
