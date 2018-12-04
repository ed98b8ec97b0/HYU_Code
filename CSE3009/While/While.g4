grammar While;

import LexerRule;

prog
  : var_decs SEMI stmts ;

var_decs
  : var_decs SEMI var_dec   # VarDecs
  | var_dec                 # VarDec
  ;

var_dec
  : ID ':' type_exp
  ;

type_exp
  : 'int'                                 # IntType
  | 'bool'                                # BoolType
  | 'array' '[' INT ']' 'of' type_exp     # ArrType
  ;

stmts
  : stmts SEMI stmt         # Stmt_list
  | stmt                    # Stmt_single
  ;
stmt
  : ID '=' expr              # Assign
  | 'print' aexp             # Print
  | 'if' bexp 'then' stmt    # If
  ;

expr
  : aexp
  | bexp
  ;

aexp
  : aexp aop aexp            # BinaryOp
  | INT                      # Int
  | ID                       # Var
  | '(' aexp ')'             # Parens
  ;

bexp
  : 'true'                   # BETrue
  | 'false'                  # BEFalse
  | '!' bexp                 # BENot
  | aexp rop aexp            # BERop
  | bexp bop bexp            # BEBop
  ;

rop
  : GT     # RopGT
  | LT     # RopLT
  ;

bop
  : AND    # BopAND
  | OR     # BopOR
  ;

aop
  : PLUS                     # Plus
  | MINUS                    # Minus
  | MULT                     # Mult
  | DIV                      # Div
  ;
