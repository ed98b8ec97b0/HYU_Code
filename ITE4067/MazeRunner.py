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
            self.roller.on_for_seconds(self.sspeed, self.stime)
            self.paper += 1
        elif (direction == (-1, 0)):
            self.roller.on_for_seconds(-self.sspeed, self.stime)
            self.paper -= 1
        elif (direction == (0, 1)):
            self.scanner.on_for_seconds(self.sspeed, self.stime)
            self.head -= 1
    
    def ending(self, line):
        for i in line:
            if i == 5:
                return True
        return False

    def running(self, commands):
        self.navigator([], reset=1)
        for command in commands:
            self.move(direction=command)
    


if __name__ == "__main__":
    from AStar import AStar

    mr = MazeRunner(sspeed=5, rspeed=5) # TODO : sspeed, rspeed 적당한 값 찾기
    line = mr.navigator(line=[], reset=1)    
    while(mr.ending(line=line)):
        line = mr.navigator(line=[])
        mr.maze.append(line)
        mr.move(direction=1)
    mr.announcer(cmap=['None', 'Black', 'Blue', 'Green', 'Yellow', 'Red', 'White', 'Brown'])
    a = AStar(init=(0,1), target=(9,5), graph=mr.maze)
    wall = a.close(x=len(a.graph), y=len(a.graph[0]))
    a.search((0,1), wall, [])
    mr.running(sorted(a.result)[0][1])
