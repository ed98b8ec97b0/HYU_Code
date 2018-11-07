#!/usr/bin/env python3
from ev3dev2.motor import LargeMotor, OUTPUT_A, OUTPUT_D, SpeedPercent, MoveTank
from ev3dev2.sensor import INPUT_2
from ev3dev2.sensor.lego import GyroSensor
from ev3dev2.sound import Sound
import math
import time
import sys

# Constants
SPEED_LOW_CAP = 43
SPEED_HIGH_CAP = 60
RUNTIME = 0.10

# device setting
sound = Sound()
gyro = GyroSensor(INPUT_2)
gyro.mode = gyro.MODE_GYRO_ANG
motor_tank = MoveTank(OUTPUT_A, OUTPUT_D)
gyro_file = open(gyro._path + "/value0", "rb")

# function for gyro sensor
def _fast_read(infile):
    """Function for fast reading from sensor files."""
    infile.seek(0)
    return(int(infile.read().decode().strip()))

def _fast_write(outfile, value):
    """Function for fast writing to motor files."""
    outfile.truncate(0)
    outfile.write(str(int(value)))
    outfile.flush()

# function for motor
def move_tank(speed, x):
    motor_tank.on_for_degrees(speed, speed, x)

def lowest_speed(x):
    if (abs(x) < SPEED_LOW_CAP):
        return SPEED_LOW_CAP
    if (abs(x) > SPEED_HIGH_CAP):
        return SPEED_HIGH_CAP
    return abs(x)

# calibration
gyro_rate = 0
gyro_offset = 0

sound.beep()
gyro_calibrate_count = 100
for i in range(gyro_calibrate_count):
    gyro_offset = gyro_offset + _fast_read(gyro_file)
    time.sleep(0.01)
gyro_offset = gyro_offset / gyro_calibrate_count

# move
sound.beep()
motor_tank.reset()
motor_tank.run_direct()
flag = 1

while(1):
    # balancing
    gyro_rate_raw = _fast_read(gyro_file)
    gyro_rate = (gyro_rate_raw - gyro_offset)
    if (flag == 1):
        flag = 0
        gyro_init = gyro_rate
        motor_tank.on_for_degrees(SPEED_LOW_CAP, SPEED_LOW_CAP, 60)
    deg = gyro_rate - gyro_init
    
    if (gyro_rate >= 0):
        move_tank(SPEED_LOW_CAP, deg)
    else:
        move_tank(-SPEED_LOW_CAP, deg)