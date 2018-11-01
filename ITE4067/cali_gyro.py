from ev3dev2.sensor import INPUT_1
from ev3dev2.sensor.lego import GyroSensor

gs = GyroSensor(INPUT_1)
gs.mode = 'GYRO-CAL'

while(1):
    ca, cas = gs.rate_and_angle
    print("Currnet Angle: {0:4d}, Current Angle Speed: {1:4d}".format(ca, cas))