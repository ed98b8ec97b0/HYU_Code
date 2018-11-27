/*
 * Simple HTML rendering test.
   - sample html cases : https://en.wikipedia.org/wiki/Help:Wikitext
 */

grammar Rendering;
/*
 * Parser.
 */
document : blocks ;

blocks : (block NEWLINE)* ;

block : TEXT                          # PLAINTEXT
      | EQs TEXT EQs                  # HEADER
      | COLONs TEXT                   # INDENT
      | SHARPs TEXT                   # SHARP
      | STARs TEXT                    # STAR
      ;

/*
 * Lexer.
 */

NEWLINE : [\r\n]+ ;
SHARPs : SHARP+ ; 
STARs : STAR+ ;
EQs : EQ+ ;
COLONs : COLON+ ;
TEXT : ~[\r\n#*=:]+ ;

/*
 * Fragments.
 */
fragment
SHARP   : '#' ;
STAR    : '*' ;
EQ      : '=' ;
COLON   : ':' ;
