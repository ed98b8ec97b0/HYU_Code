#!/usr/bin/env python3
from ev3dev2.motor import LargeMotor, OUTPUT_A, OUTPUT_D, SpeedPercent, MoveTank
from ev3dev2.sensor import INPUT_1
from ev3dev2.power import PowerSupply
from ev3dev2.sensor.lego import GyroSensor
from ev3dev2.sound import Sound
import math
import time
import sys

# Constants
RAD_PER_DEG = math.pi / 180

# EV3 Platform specific constants

# For the LEGO EV3 Gyro in Rate mode, 1 unit = 1 deg/s
DEG_PER_SEC_PER_RAW_GYRO_UNIT = 1

# Express the above as the rate in rad/s per gyro unit
RAD_PER_SEC_PER_RAW_GYRO_UNIT = DEG_PER_SEC_PER_RAW_GYRO_UNIT * RAD_PER_DEG

# For the LEGO EV3 Large Motor 1 unit = 1 deg
DEG_PER_RAW_MOTOR_UNIT = 1

# Express the above as the angle in rad per motor unit
RAD_PER_RAW_MOTOR_UNIT = DEG_PER_RAW_MOTOR_UNIT * RAD_PER_DEG

# On the EV3, "1% speed" corresponds to 1.7 RPM (if speed
# control were enabled).
RPM_PER_PERCENT_SPEED = 1.7

# Convert this number to the speed in deg/s per "percent speed"
DEG_PER_SEC_PER_PERCENT_SPEED = RPM_PER_PERCENT_SPEED * 360 / 60

# Convert this number to the speed in rad/s per "percent speed"
RAD_PER_SEC_PER_PERCENT_SPEED = DEG_PER_SEC_PER_PERCENT_SPEED * RAD_PER_DEG

# Speed and steering limits
SPEED_MAX = 46
STEER_MAX = 8
RUNTIME = 0.08

sound = Sound()
gyro = GyroSensor()
gyro.mode = gyro.MODE_GYRO_RATE
# motor_left = LargeMotor(OUTPUT_A)
# motor_right = LargeMotor(OUTPUT_D)
motor_tank = MoveTank(OUTPUT_A, OUTPUT_D)
gyro_file = open(gyro._path + "/value0", "rb")
# encoder_left_file = open(motor_left._path + "/position", "rb")
# encoder_right_file = open(motor_right._path + "/position", "rb")
# dc_left_file = open(motor_left._path + "/duty_cycle_sp", "w")
# dc_right_file = open(motor_right._path + "/duty_cycle_sp","w")

def _fast_read(infile):
    """Function for fast reading from sensor files."""
    infile.seek(0)
    return(int(infile.read().decode().strip()))

def _fast_write(outfile, value):
    """Function for fast writing to motor files."""
    outfile.truncate(0)
    outfile.write(str(int(value)))
    outfile.flush()

# motor_angle_raw = 0
# motor_angle = 0
# motor_angle_ref = 0

gyro_rate = 0
gyro_est_angle = 0
gyro_offset = 0

sound.beep()

# calibration
gyro_calibrate_count = 100
for i in range(gyro_calibrate_count):
    gyro_offset = gyro_offset + _fast_read(gyro_file)
    time.sleep(0.01)
gyro_offset = gyro_offset / gyro_calibrate_count

sound.beep()

motor_tank.reset()
motor_tank.run_direct()

def move_tank(speed, x):
    motor_tank.on_for_seconds(speed, speed, x)

while(1):
    gyro_rate_raw = _fast_read(gyro_file)
    gyro_rate = (gyro_rate_raw - gyro_offset) * RAD_PER_SEC_PER_RAW_GYRO_UNIT
    if (gyro_rate >= 0):
        move_tank(SPEED_MAX, RUNTIME)
    else:
        move_tank(-SPEED_MAX, RUNTIME)

