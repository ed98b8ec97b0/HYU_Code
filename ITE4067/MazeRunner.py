from ev3dev2.motor import LargeMotor, MediumMotor, OUTPUT_A, OUTPUT_B, OUTPUT_C
from ev3dev2.sensor import INPUT_1
from ev3dev2.sensor.lego import ColorSensor
import threading, time

class MazeRunner:
    def __init__(self, sspeed = 5.3, stime = 1, rspeed = 6.8, rtime = 1, mode = 'COL-COLOR'):
        self.sspeed = sspeed
        self.stime = stime
        self.rspeed = rspeed
        self.rtime = rtime
        self.cmap = ['None', 'Black', 'Blue', 'Green', 'Yellow', 'Red', 'White', 'Brown']
        self.roller = LargeMotor(OUTPUT_A)
        self.scanner = LargeMotor(OUTPUT_B)
        # self.runner = MediumMotor(OUTPUT_C)
        self.cs = ColorSensor(INPUT_1)
        self.cs.mode = mode
        self.maze = []
        print("{}, {}, {}, {}, {}".format(self.sspeed, self.stime, self.rspeed, self.rtime, self.cs.mode))

    def navigator(self):
        for _ in range(5):
            for _ in range(9):
                self.scanner.on_for_seconds(self.sspeed, self.stime)
                self.maze.append(self.cs.value())
            self.roller.on_for_seconds(self.rspeed, self.rtime)
            for _ in range(9):
                self.scanner.on_for_seconds(-self.sspeed, self.stime)
                self.maze.append(self.cs.value())
            self.roller.on_for_seconds(self.rspeed, self.rtime)
        self.roller.on_for_seconds(-self.rspeed, self.rtime * 10)
            
    def announcer(self):
        for l in range(int(len(self.maze)/10) - 1):
            for p in range(10):
                if (p != 9):
                    print("{:<6}".format(self.cmap[self.maze[p + l * 10]]), end=" ")
                else:
                    print("{:<6}".format(self.cmap[self.maze[p + l * 10]]))

    def 



mr = MazeRunner(sspeed=5, rspeed=5)
mr.navigator()
mr.announcer()