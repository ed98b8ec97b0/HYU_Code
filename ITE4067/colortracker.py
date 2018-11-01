#!/usr/bin/env python3
from ev3dev2.motor import LargeMotor, OUTPUT_A, OUTPUT_D, SpeedPercent, MoveTank
from ev3dev2.sensor import INPUT_1, INPUT_4
from ev3dev2.sensor.lego import ColorSensor
import time

color = ('No_color', 'Black', 'Blue', 'Green', 'Yellow', 'Red', 'White', 'Brown')

tank_drive = MoveTank(OUTPUT_A, OUTPUT_D)

cs1 = ColorSensor(INPUT_1)
cs2 = ColorSensor(INPUT_4)
cs1.mode = 'COL-COLOR'
cs2.mode = 'COL-COLOR'

runtime = 0.35
turntime = 0.35
runspeed = 100
turnspeed = 50

wait = 1
left = 0
right = 0

while True:
    # print(color[cs1.value()] + "__" + color[cs2.value()])
    if cs1.value() == 5 or cs2.value() == 5:
        print('Stop')
        break
    elif (cs1.value() == 3 or cs2.value() == 3) and wait == 1:
        print('Wait')
        wait = 0
        time.sleep(3)
        tank_drive.on_for_seconds(SpeedPercent(runspeed), SpeedPercent(runspeed), runtime)
    elif (cs1.value() == 1 and cs2.value() != 1):
        print('Turn left')
        left = 1
        right = 0
        tank_drive.on_for_seconds(SpeedPercent(0), SpeedPercent(turnspeed), turntime)
    elif (cs1.value() != 1 and cs2.value() == 1):
        print('Turn right')
        left = 0
        right = 1
        tank_drive.on_for_seconds(SpeedPercent(turnspeed), SpeedPercent(0), turntime)
    elif (cs1.value() == 1 and cs2.value() == 1):
        tank_drive.on_for_seconds(SpeedPercent(runspeed), SpeedPercent(runspeed), runtime)
    else:
        if left == 1:
            print('No color,  Turn left')
            tank_drive.on_for_seconds(SpeedPercent(0), SpeedPercent(turnspeed), turntime)
        elif right == 1:
            print('No color, Turn right')
            tank_drive.on_for_seconds(SpeedPercent(turnspeed), SpeedPercent(0), turntime)
        else:
            print('No color')
