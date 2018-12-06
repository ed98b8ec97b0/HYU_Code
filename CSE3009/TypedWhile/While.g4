grammar While;

import LexerRule;

prog
  : var_decls SEMI stmts ;
var_decls
  : var_decls SEMI var_decl # VarDecs
  | var_decl                # VarDec
  ;

var_decl
  : ID ':' type_exp ;

type_exp
  : 'int'                             # IntType
  | 'bool'                            # BoolType
  | 'array' '[' INT ']' 'of' type_exp # ArrType
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
  : aexp                     # ExprA
  | bexp                     # ExprB
  ;

aexp
  : aexp aop aexp            # AE_OP
  | INT                      # AE_Int
  | ID                       # AE_Var
  | '(' aexp ')'             # AE_Parens
  ;

bexp
  : bexp bop bexp            # BE_BOP
  | aexp rop aexp            # BE_ROP
  | 'true'                   # BE_True
  | 'false'                  # BE_False
  | '!' bexp                 # BE_Not
  | ID                       # BE_Var
  | '(' bexp ')'             # BE_Parens
  ;

// TODO: GE(>=) 추가
// TODO: LE(<=) 추가
// TODO: EQ(==) 추가
// TODO: NEQ(!=) 추가

rop
  : GT                       # ROP_GT
  | LT                       # ROP_LT
  | GE                       # ROP_GE
  | LE                       # ROP_LE
  | EQ                       # ROP_EQ
  | NEQ                      # ROP_NEQ
  ;

// TODO: XOR(^) 추가

bop
  : AND                      # BOP_AND
  | OR                       # BOP_OR
  | XOR                      # BOP_XOR
  ;

aop
  : PLUS                     # AOP_PLUS
  | MINUS                    # AOP_MINUS
  | MULT                     # AOP_MULT
  | DIV                      # AOP_DIV
  ;