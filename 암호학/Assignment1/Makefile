CC = gcc
OBJS = AES128.c test_AES128.c
TARGET = AES128

all : $(TARGET)

$(TARGET): $(OBJS)
	$(CC) -o $@ $(OBJS)
	./$(TARGET)
	make clean

clean :
	rm -f $(TARGET)
