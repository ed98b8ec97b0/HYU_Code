#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <time.h>

enum rsp {paper, rock, scissors, game, help, instructions, quit};
enum outcome {win, lose, tie, error};

typedef enum rsp rsp;
typedef enum outcome outcome;

outcome compare(rsp player_choice, rsp machine_choice);
void prn_final_status(int win_cnt, int lose_cnt);
void prn_game_status(int win_cnt, int lose_cnt, int tie_cnt);
void prn_help(void);
void prn_instructions(void);
void report_and_tabulate(outcome result, int *win_cnt_ptr, int *lose_cnt_ptr, int *tie_cnt_ptr);
rsp selection_by_machine(void);
rsp selection_by_player(void);