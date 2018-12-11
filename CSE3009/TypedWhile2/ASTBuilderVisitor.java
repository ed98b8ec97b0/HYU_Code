// Generated from While.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class ASTBuilderVisitor extends WhileBaseVisitor<Node> {

  TypeEnv te;

  @Override public Node visitProg(WhileParser.ProgContext ctx) {
    // * prog : var_decls SEMI stmts
    // 이 함수에 의해 코드들을 위에서 부터 차례로 싹 다 읽어내려간다.
    // 그리고 마지막에 그 결과물을 AstwithEval로 넣어 AST로 만들어 넣는다.
    Declaration d = (Declaration)visit(ctx.var_decls());
    te = d.execute(new TypeEnv());
    Statement s = (Statement)visit(ctx.stmts());
    return new AstWithEval(d, s);
  }

  @Override public Node visitVarDecs(WhileParser.VarDecsContext ctx) {
    // * var_decls : var_decls SEMI var_decl
    // var_decls ; var_decl을 var_decl만 있을 때까지 들어간다.
    Declaration ds = (Declaration)visit(ctx.var_decls());
    Declaration d = (Declaration)visit(ctx.var_decl());
    // 처음으로 여기 들어오는 시점에선 var_decls가 없기때문에 ds.getClass()가 Decls 클래스가 아니다.
    // ? null 클래스 혹은 Declaration 클래스로 예상된다.
    if (ds.getClass() == Decls.class) {
      // 두번째 var_decls부터는 처음에 만든 Decls 클래스에 게속 넣기만 하는 것.
      // Vector<Declaration> ds 안에 계속해서 Declaration d가 추가됨.
      return ((Decls)ds).append(d);
    } else {
      // ! Declaration줄 중 가장 첫 줄.
      // ! 이 줄에서 처음으로 Decls 클래스를 만들고 그 안에 Declaration을 넣는다.
      // 이때 ds는 Vector<Declaration>이다.
      return new Decls().append(ds).append(d);
    }
  }

  @Override public Node visitVarDec(WhileParser.VarDecContext ctx) {
    // * var_decls : var_decl
    // Declaration 한줄로만 이루어진 클래스.
    // var_decl을 처리만 하면 된다.
    return visit(ctx.var_decl());
  }

  @Override public Node visitVar_decl(WhileParser.Var_declContext ctx) {
    // * var_decl : ID ':' type_exp
    Type t = (Type)visit(ctx.type_exp()); // 타입을 가져옴.
    Variable v = new Variable(ctx.ID().getText(), t); // 가져온 타입과 주어진 ID를 이용하여 새로운 Variable를 만든다.
    return new VarDecl(v); // 만든 Variable를 AstWithEval의 VarDecl 클래스에 넣어 사용.
  }

  @Override public Node visitIntType(WhileParser.IntTypeContext ctx) {
    // * type_exp : 'int'
    return new IntType();
  }

  @Override public Node visitBoolType(WhileParser.BoolTypeContext ctx) {
    // * type_exp : 'bool'
    return new BoolType();
  }

  @Override public Node visitArrType(WhileParser.ArrTypeContext ctx) {
    // * type_exp : 'array' '[' INT ']' 'of' 'type_exp'
    int size = Integer.valueOf(ctx.INT().getText());
    Type t = (Type)visit(ctx.type_exp());
    return new ArrType(size, t);
  }

  @Override public Node visitStmt_list(WhileParser.Stmt_listContext ctx) {
    // * stmts : stmts SEMI stmt
    // 앞서 사용한 var_decls와 마찬가지로 stmt 하나만 있는 시점까지 계속 들어간다.
    Statement ss = (Statement)visit(ctx.stmts());
    Statement s = (Statement)visit(ctx.stmt());
    // 이 라인에 처음 올 때는 stmt 첫 줄.
    if (ss.getClass() == Stmts.class) {
      // 앞서 만든 벡터에 Statement들을 추가하는 것.
      return ((Stmts)ss).append(s);
    } else {
      // ! 제일 처음 Statement로 이 때, Statement의 벡터 집합인 Vector<Statement>를 만든다.
      // ! Vector<Statement> Stmts에 제일 첫 Statement stmt를 넣는 것.
      return new Stmts().append(ss).append(s);
    }
  }

  @Override public Node visitStmt_single(WhileParser.Stmt_singleContext ctx) {
    // * stmts : stmt
    return (Statement)visit(ctx.stmt());
  }

  @Override public Node visitAssign(WhileParser.AssignContext ctx) {
    // * stmt : ID '=' expr
    String name = ctx.ID().getText(); // ID에 값을 넣기 위해 ID를 받아옴
    Type t = te.lookup(name); // 타입 환경에서 ID를 이용해 타입을 받아온다.
    Variable v = new Variable(name, t); // 받아온 ID와 타입을 이용해 새로운 Variable를 생성.
    Expression e = (Expression)visit(ctx.expr()); // Variable에 넣기 위한 expr에 접근.
    return new Assign(v, e); // 처리한 Variable와 Expression을 새로운 Assign으로 만듬.
  }

  @Override public Node visitExprA(WhileParser.ExprAContext ctx) {
    // * expr : aexp
    return (Aexp)visit(ctx.aexp());
  }

  @Override public Node visitExprB(WhileParser.ExprBContext ctx) {
    // * expr : bexp
    return (Bexp)visit(ctx.bexp());
  }

  @Override public Node visitPrint(WhileParser.PrintContext ctx) {
    // * stmt : 'print' aexp
    Aexp e = (Aexp)visit(ctx.aexp());
    return new Print(e);
  }

  @Override public Node visitSkip(WhileParser.SkipContext ctx) {
    // * stmt : 'skip'
    return new Skip();
  }

  @Override public Node visitIf(WhileParser.IfContext ctx) {
    // * stmt : 'if' bexp 'then' stmt
    Bexp cond = (Bexp)visit(ctx.bexp());
    Statement s = (Statement)visit(ctx.stmt());
    return new If(cond, s);
  }

  @Override public Node visitWhile(WhileParser.WhileContext ctx) {
    // * stmt : 'while' bexp 'do' stmt 'od'
    Bexp cond = (Bexp)visit(ctx.bexp());
    Statement s = (Statement)visit(ctx.stmts());
    return new While(cond, s);
  }

  @Override public Node visitAE_Var(WhileParser.AE_VarContext ctx) {
    // * aexp : ID
    String name = ctx.ID().getText(); // 1. ID를 받아온다.
    Type t = te.lookup(name); // 2. ID의 Type을 받아온다.
    Variable v = new Variable(name, t); // 3. ID와 Type으로 새로운 Variable를 만든다.
    return new AEid(v);
  }

  @Override public Node visitAE_Parens(WhileParser.AE_ParensContext ctx) {
    // * aexp : '(' aexp ')'
    return (Aexp)visit(ctx.aexp());
  }

  @Override public Node visitAE_Int(WhileParser.AE_IntContext ctx) {
    // * aexp : INT
    return new AEint(Integer.valueOf(ctx.INT().getText()));
  }

  @Override public Node visitAE_OP(WhileParser.AE_OPContext ctx) {
    // * aexp : aexp aop aexp
    Aexp left = (Aexp)visit(ctx.aexp(0));
    Aexp right = (Aexp)visit(ctx.aexp(1));
    Aop aop = (Aop)visit(ctx.aop());
    return new AEop(left, aop, right);
  }

  @Override public Node visitBE_Parens(WhileParser.BE_ParensContext ctx) {
    // * bexp : '(' bexp ')'
    return (Bexp)visit(ctx.bexp());
  }

  @Override public Node visitBE_BOP(WhileParser.BE_BOPContext ctx) {
    // * bexp : bexp bop bexp
    Bexp left = (Bexp)visit(ctx.bexp(0));
    Bexp right = (Bexp)visit(ctx.bexp(1));
    Bop bop = (Bop)visit(ctx.bop());
    return new BEop(left, bop, right);
  }

  @Override public Node visitBE_ROP(WhileParser.BE_ROPContext ctx) {
    // * bexp : bexp rop bexp
    Aexp left = (Aexp)visit(ctx.aexp(0));
    Aexp right = (Aexp)visit(ctx.aexp(1));
    Rop rop = (Rop)visit(ctx.rop());
    return new BErop(left, rop, right);
  }

  @Override public Node visitBE_True(WhileParser.BE_TrueContext ctx) {
    // * bexp = 'true'
    return new BEtrue();
  }

  @Override public Node visitBE_False(WhileParser.BE_FalseContext ctx) {
    // * bexp = 'false'
    return new BEfalse();
  }

  @Override public Node visitBE_Not(WhileParser.BE_NotContext ctx) {
    // * bexp = '!' bexp
    return new BEnot((Bexp)visit(ctx.bexp()));
  }

  @Override public Node visitBE_Var(WhileParser.BE_VarContext ctx) {
    // * bexp = ID
    String name = ctx.ID().getText();
    Type t = te.lookup(name);
    Variable v = new Variable(name, t); // Variable이 맞는지 확인하는 작업
    return new BEid(v);
  }

  @Override public Node visitROP_GT(WhileParser.ROP_GTContext ctx) {
    // * rop : GT
    return new RopGT();
  }

  @Override public Node visitROP_LT(WhileParser.ROP_LTContext ctx) {
    // * rop : LT
    return new RopLT();
  }

  @Override public Node visitBOP_AND(WhileParser.BOP_ANDContext ctx) {
    // * bop : AND
    return new BopAND();
  }

  @Override public Node visitBOP_OR(WhileParser.BOP_ORContext ctx) {
    // * bop : OP
    return new BopOR();
  }

  @Override public Node visitAOP_PLUS(WhileParser.AOP_PLUSContext ctx) {
    // * aop : PLUS
    return new AopPLUS();
  }

  @Override public Node visitAOP_MINUS(WhileParser.AOP_MINUSContext ctx) {
    // * aop : MINUS
    return new AopMINUS();
  }

  @Override public Node visitAOP_MULT(WhileParser.AOP_MULTContext ctx) {
    // * aop : MULT
    return new AopMULT();
  }

  @Override public Node visitAOP_DIV(WhileParser.AOP_DIVContext ctx) {
    // * aop : DIV
    return new AopDIV();
  }
}