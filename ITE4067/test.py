#!/usr/bin/env python3

from ev3dev2.motor import OUTPUT_A, OUTPUT_B, OUTPUT_C
from ev3dev2.motor import LargeMotor, MediumMotor
from ev3dev2.sensor import INPUT_1
from ev3dev2.sensor.lego import ColorSensor
from ev3dev2.sound import Sound

SENSOR_SPEED = 5
SENSOR_TIME = 1
PAPER_SPEED = 5
PAPER_TIME = 1.5
MAP = ['무', '검', '파', '초', '노', '빨', '흰', '갈']

pm = LargeMotor(OUTPUT_A)
cp = LargeMotor(OUTPUT_B)
cs = ColorSensor(INPUT_1)
cs.mode = 'COL-COLOR'
map = []

def move_left(n):
    cp.on_for_seconds(-SENSOR_SPEED, SENSOR_TIME * n)

def move_right(n):
    cp.on_for_seconds(SENSOR_SPEED, SENSOR_TIME * n)

def inroll(n):
    pm.on_for_seconds(PAPER_SPEED, PAPER_TIME * n)

def outroll(n):
    pm.on_for_seconds(-PAPER_SPEED, PAPER_TIME * n)

def map_print(map):
    for i in range(len(map)):
        print(MAP[map[i]])

def scan_map(map):
    for i in range(0, 9):
        map.append([])
        for _ in range(0, 9):
            map[i].append(cs.value())
            if (i % 2 == 0):
                move_right(1)
            else:
                move_left(1)
            map_print(map)
        inroll(1)

# initial
# move_left(9)

# scan
# scan_map(map)

# check
move_left(9)
# move_right(9)