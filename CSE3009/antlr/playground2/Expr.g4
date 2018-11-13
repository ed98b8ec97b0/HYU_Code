grammar Expr;		
prog:	(expr NEWLINE)* ;
expr:	expr MULTI expr     # MULTI
    |	expr ADD expr     # ADD
    |	INT                     # INT
    |	'(' expr ')'            # BARC
    ;
NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;
MULTI   : [*-] ;
ADD     : [+-] ;