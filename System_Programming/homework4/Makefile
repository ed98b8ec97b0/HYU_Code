CC = gcc
OBJS = main.c exec_cmd.c exec_cmd_bg.c history.c cd.c parse_cmd.c
TARGET = smsh

all : $(TARGET)

$(TARGET): $(OBJS)
	$(CC) -o $@ $(OBJS)
	./smsh
	rm smsh
