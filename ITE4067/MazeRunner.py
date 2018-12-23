#!/usr/bin/env python3
from ev3dev2.motor import LargeMotor, MediumMotor, OUTPUT_A, OUTPUT_B, OUTPUT_C
from ev3dev2.sensor import INPUT_1
from ev3dev2.sensor.lego import ColorSensor
import threading, time

class MazeRunner:
    def __init__(self, sspeed=5.3, stime=1, rspeed=6.8, rtime=1, mode='COL-COLOR'):
        self.sspeed = sspeed
        self.stime = stime
        self.rspeed = rspeed
        self.rtime = rtime
        self.roller = LargeMotor(OUTPUT_A)
        self.scanner = LargeMotor(OUTPUT_B)
        # self.runner = MediumMotor(OUTPUT_C)
        self.cs = ColorSensor(INPUT_1)
        self.cs.mode = mode
        self.maze = []
        self.head = 0
        self.paper = 0
        self.target = (0, 0)

    def navigator(self, line, reset=0):
        if (reset == 0):
            if (self.head == 0):
                for _ in range(10):
                    self.move((0, 1))
                    line.append(self.cs.value())
                return line
            elif (self.head == 9):
                for _ in range(10):
                    self.move((0, -1))
                    line.append(self.cs.value())
                return line.reverse()
        else:
            for _ in range(self.head):
                self.move((0, -1))
            for _ in range(self.paper):
                self.move((-1, 0))
            return line

    def seeker(self):
        end = True
        while (end):
            line = []
            if (self.head == 0):
                for _ in range(10):
                    color = self.cs.value()
                    if (color == 5):
                        end = False
                        self.target = (self.head, self.paper)
                    line.append(color)
                    if (self.head != 9):
                        self.move((0,1))
                self.maze.append(line)
            elif (self.head == 9):
                for _ in range(10):
                    color = self.cs.value()
                    if (color == 5):
                        end = False
                        self.target = (self.head, self.paper)
                    line.append(color)
                    if (self.head != 0):
                        self.move((0,-1))
                line.reverse()
                self.maze.append(line)
            self.move((1,0))
        for _ in range(self.head):
            self.move((0,-1))
        for _ in range(self.paper):
            self.move((-1,0))
            

    def announcer(self, cmap):
        for l in range(int(len(self.maze)/10) - 1):
            for p in range(10):
                if (p != 9):
                    print("{:<6}".format(cmap[self.maze[p + l * 10]]), end=" ")
                else:
                    print("{:<6}".format(cmap[self.maze[p + l * 10]]))

    def move(self, direction):
        if (direction == (0, 1)):
            self.scanner.on_for_seconds(self.sspeed, self.stime)
            self.head += 1
        elif (direction == (1, 0)):
            self.roller.on_for_seconds(self.rspeed, self.rtime)
            self.paper += 1
        elif (direction == (-1, 0)):
            self.roller.on_for_seconds(-self.rspeed, self.rtime)
            self.paper -= 1
        elif (direction == (0, -1)):
            self.scanner.on_for_seconds(-self.sspeed, self.stime)
            self.head -= 1
    
    def ending(self, line):
        print(line)
        for i in line:
            if i == 5:
                return False
        return True

    def running(self, commands):
        for command in commands:
            print(command)
            self.move(direction=command)
    


if __name__ == "__main__":
    from AStar import AStar

    mr = MazeRunner(sspeed=5.3, rspeed=6.8) # TODO : sspeed, rspeed 적당한 값 찾기
    mr.seeker()
    for line in mr.maze:
        print(line)
    mr.move((0,1))
    mr.move((0,1))
    mr.move((0,1))
    a = AStar(init=(0,3), target=mr.target, graph=mr.maze)
    wall = a.close(x=len(a.graph), y=len(a.graph[0]))
    a.search((0,1), wall, [])
    print(sorted(a.result[0][1]))
    mr.running(sorted(a.result)[0][1])
