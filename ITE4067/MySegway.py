#!/usr/bin/env python3
from ev3dev2.motor import LargeMotor, OUTPUT_A, OUTPUT_D, SpeedPercent, MoveTank
from ev3dev2.sensor import INPUT_2
from ev3dev2.sensor.lego import GyroSensor
from ev3dev2.sound import Sound
import math
import time
import sys

# Constants
RAD_PER_DEG = math.pi / 180
DEG_PER_SEC_PER_RAW_GYRO_UNIT = 1
RAD_PER_SEC_PER_RAW_GYRO_UNIT = DEG_PER_SEC_PER_RAW_GYRO_UNIT * RAD_PER_DEG
SPEED_CONSTANT = 44
RUNTIME = 0.1

# device setting
sound = Sound()
gyro = GyroSensor(INPUT_2)
gyro.mode = gyro.MODE_GYRO_RATE
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
    motor_tank.on_for_seconds(speed, speed, x)

# calibration
gyro_rate = 0
gyro_est_angle = 0
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

while(1):
    # balancing
    gyro_rate_raw = _fast_read(gyro_file)
    gyro_rate = (gyro_rate_raw - gyro_offset) * RAD_PER_SEC_PER_RAW_GYRO_UNIT
    if (gyro_rate >= 0.1):
        move_tank(SPEED_CONSTANT, RUNTIME)
    elif (gyro_rate <= -0.1):
        move_tank(-SPEED_CONSTANT, RUNTIME)
    else:
        move_tank(SPEED_CONSTANT, RUNTIME * 2)