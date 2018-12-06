#!/usr/bin/env python3

from ev3dev2.motor import OUTPUT_A, OUTPUT_B, OUTPUT_C
from ev3dev2.motor import LargeMotor, MediumMotor
from ev3dev2.sensor import INPUT_1
from ev3dev2.sensor.lego import ColorSensor
from ev3dev2.sound import Sound
import threading, time

SENSOR_SPEED = 5.3
SENSOR_TIME = 1
PAPER_SPEED = 6
PAPER_TIME = 1
MAP = ['무', '검', '파', '초', '노', '빨', '흰', '갈']
status = False
line = 0

pm = LargeMotor(OUTPUT_A)
cp = LargeMotor(OUTPUT_B)
cs = ColorSensor(INPUT_1)
cs.mode = 'RGB-RAW'
all_map = []
in_map = []

def move_left(n=1):
    cp.on_for_seconds(-SENSOR_SPEED, SENSOR_TIME * n)

def move_right(n=1):
    cp.on_for_seconds(SENSOR_SPEED, SENSOR_TIME * n)

def inroll(n=1):
    pm.on_for_seconds(PAPER_SPEED, PAPER_TIME * n)

def outroll(n=1):
    pm.on_for_seconds(-PAPER_SPEED, PAPER_TIME * n)

def map_print(target=all_map):
    for i in range(len(target)):
        print(target[i])

def scan_color(status):
    while(status):
        all_map[line].append(MAP[cs.value()])
        time.sleep(1)

def color_picker(rgb):
    if (rgb <= 207 and rgb >= 201):
        return "W"
    elif (rgb <= 21 and rgb >= 15):
        return "B"
    elif (rgb <= 58 and rgb >= 52):
        return "S"
    elif (rgb <= 179 and rgb >= 171):
        return "E"

def start():
    for _ in range(5):
        for _ in range(9):
            in_map.append(cs.value())
            move_left()

        in_map.append(cs.value())
        print(in_map)
        in_map.clear()
        inroll(1)

        for _ in range(9):
            in_map.append(cs.value())
            move_right()

        in_map.append(cs.value())
        print(in_map)
        in_map.clear()
        inroll(1)
    outroll(10)


# start()
inroll(10)

# Red
# MAX: 179
# MIN: 171
# Mean: 174.06

# Green
# Max: 58
# Min: 52
# Mean: 54.84

# Black
# Max: 21
# Min: 15
# Mean: 18.2

# White
# Max: 207
# Min: 201
# Mean: 203.06