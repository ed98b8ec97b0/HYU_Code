CC = gcc
OBJS = main.c
TARGET = dmm

all : $(TARGET)

$(TARGET): $(OBJS)
	$(CC) -o $@ $(OBJS)
	./$(TARGET)
	make clean

clean :
	rm -f $(TARGET)
