%{
#include <stdio.h>
int num_strike = 0
int num_ball = 0
int num_out = 0
int team = 1
int score = [0, 0]
int stage = 1

int strike (int num_strike) {
    if (num_strike < 2) {
        return num_strike++;
    }
    else {
        return 0;
    }
}

int ball (int num_ball) {
    if (num_ball < 3) {
        return num_ball++;
    }
    else {
        return 0;
    }
}

int out (int num_out) {
    if (num_out < 2) {
        return num_out++;
    }
    else {
        return 0;
    }
}
%}

%%
S|s 
H|h
B|b