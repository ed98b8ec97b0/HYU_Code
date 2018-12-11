class Optimizer {
  // 최적화에 관련된 함수
  // changed = 최적화가 수행되면 true
  // opt_* = true인 최적화만 수행
  static boolean changed = false;

  static boolean opt_ce = false;
  static boolean opt_eb = false;
  static boolean opt_rs = false;
  static boolean opt_cf = false;

  AstWithEval run(AstWithEval ast) {
    DeadCode dc = new DeadCode();
    Simplifier smp = new Simplifier();
    do {
      changed = false;
      // 최적화 함수 내에서 changed가 true로 변한다.
      if (opt_ce)
        ast = dc.simpleCondElimination(ast);
      if (opt_eb)
        ast = smp.remove_empty_blocks(ast);
      if (opt_rs)
        ast = smp.remove_skip(ast);
      if (opt_cf)
        ast = smp.constant_folding(ast);
    } while(changed);
    return ast;
  }

  class Simplifier {
    // * constant_folding 함수들은 수식으로 이루어진 부분들을 해당 시점의 상수로 바꿔주는 함수들이다.
    AstWithEval constant_folding(AstWithEval ast) {
      // ast가 들어오면 stmt만 골라서 보낸다.
      ast.s = constant_folding_Stmt(ast.s);
      return ast;
    }

    Statement constant_folding_Stmt(Statement s) {
      if (s instanceof Stmts) {
        // folding이 들어왔을 때, 제일 처음 들어올 부분.
        // 1. 우선 처리가 끝난 Statement들을 담을 block을 하나 만든다.
        Stmts block = new Stmts();
        for (Statement si : ((Stmts)s).ss) {
          // 2. Stmts 안에 들어있는 Statement들을 순차적으로 constant_folding_Stmt에 넣고 그 결과물을 block에 넣는다.
          block.append(constant_folding_Stmt(si));
        }
        return block; // 3. 모든 Statement에 대한 constant_folding이 끝났으므로 block을 return
      } else if (s instanceof Print) {
        // Expression 클래스에 있는 fromValue 함수를 이용해서 계산한 것을 다시 반환.
        Expression e = ((Print)s).e;
        Value v = e.eval(new Env());
        return new Print(e.fromValue(v));
      } else if (s instanceof If) {
        // 마찬가지로 If 문도 Conditional 부분에서 한번.
        // Statements 부분에서 다시 constant_folding_Stmt를 수행한다.
        Bexp cond = (Bexp)(((If)s).e);
        Statement body = ((If)s).s;
        Value v = cond.eval(new Env());
        body = constant_folding_Stmt(body);
        return new If(cond.fromValue(v), body);
      } else if (s instanceof While) {
        // While 문도 마찬가지. body는 따로 최적화를 돌린다.
        // 최적화가 이루어진 Expression과 Statements를 다시 합쳐서 하나의 While로 만드는 것.
        Bexp cond = (Bexp)(((While)s).e);
        Statement body = ((While)s).s;
        Value v = cond.eval(new Env());
        body = constant_folding_Stmt(body);
        return new While(cond.fromValue(v), body);
      } else if (s instanceof Assign) {
        // Assign의 경우 rhs만 최적화 하면 돼서 이렇게 만듬.
        Variable lhs = ((Assign)s).lhs;
        Expression rhs = ((Assign)s).rhs;
        Value v = rhs.eval(new Env());
        return new Assign(lhs, rhs.fromValue(v));
      } else {
        return s;
      }
    }


    // TODO: 이쪽까지 하는 걸로 구현해보기.
    Expression constant_folding_E(Expression e) {
      return e;
    }

    Aexp constant_folding_AE(Aexp e) {
      return e;
    }

    Bexp constant_folding_BE(Bexp e) {
      return e;
    }

    // * remove_empty 함수들은 Stmts가 비어있으면 여기에 skip을 채워주는 함수들이다.
    AstWithEval remove_empty_blocks(AstWithEval ast) {
      // ast에서 statement만 받아와서 넘겨주는 클래스.
      // 작동 방식은 constant_folding이랑 거의 흡사하다.
      ast.s = remove_empty_blocks_Stmt(ast.s);
      return ast;
    }

    Statement remove_empty_blocks_Stmt(Statement s) {
      if (s instanceof Stmts) {
        Stmts block = new Stmts();
        for (Statement si : ((Stmts)s).ss) {
          block.append(remove_empty_blocks_Stmt(si));
        }
        if (block.ss.isEmpty()) {
          // 이부분이 차이가 있다.
          // block 안이 비어있으면 Skip을 넣어주는 것.
          changed = true;
          return new Skip();
        } else {
          return block;
        }
      } else if (s instanceof If) {
        Bexp cond = (Bexp)(((If)s).e);
        Statement body = ((If)s).s;
        body = remove_empty_blocks_Stmt(body);
        return new If(cond, body);
      } else if (s instanceof While) {
        Bexp cond = (Bexp)(((While)s).e);
        Statement body = ((While)s).s;
        body = remove_empty_blocks_Stmt(body);
        return new While(cond, body);
      } else {
        return s;
      }
      // ? remove_empty는 왜 experssion 별로 따로 설정할 필요가 없을까? => empty는 [cond, body] 구조에서 body가 비어있는 것을 지우기 때문이다.
    }

    // * remove_skip 시리즈는 stmts에서 skip 클래스를 모두 제거하는 함수이다.
    AstWithEval remove_skip(AstWithEval ast) {
      ast.s = remove_skip_Stmt(ast.s);
      return ast;
    }

    Statement remove_skip_Stmt(Statement s) {
      // 마찬가지로 똑같은 행위를 하나 Skip인 경우 block에 넣지않고 넘기는 것. 
      if (s instanceof Stmts) {
        Stmts block = new Stmts();
        for (Statement si : ((Stmts)s).ss) {
          si = remove_skip_Stmt(si);
          if (si instanceof Skip) {
            changed = true;
          } else {
            block.append(si);
          }
        }
        return block;
      } else if (s instanceof If) {
        Bexp cond = (Bexp)(((If)s).e);
        Statement body = ((If)s).s;
        body = remove_skip_Stmt(body);
        if (body instanceof Skip) {
          // ? 이미 Skip인데 다시 Skip을 넘기는 이유는 뭘까? => If문의 Body는 Statements의 형태이기 때문.
          changed = true;
          return new Skip();
        } else {
          return new If(cond, body);
        }
      } else if (s instanceof While) {
        Bexp cond = (Bexp)(((While)s).e);
        Statement body = ((While)s).s;
        body = remove_skip_Stmt(body);
        // ! body를 그대로 넣고 while문을 만드는 이유: 사용자가 이런 구조를 의도한 것일 수도 있기 때문.
        return new While(cond, body);
      } else {
        return s;
      }
    }
  } 

  class DeadCode {
    // * 사용되지 않는 코드를 날리는 클래스.
    // * If 3 < 0 then ~ 과 같은 클래스를 Skip으로 만든다.
    // 전체적인 구조는 앞서 봐왔던 클래스와 동일하다.
    AstWithEval simpleCondElimination(AstWithEval ast) {
      ast.s = simpleCE_Stmt(ast.s);
      return ast;
    }

    Statement simpleCE_Stmt(Statement s) {
      if (s instanceof Stmts) {
        Stmts ss = new Stmts();
        for (Statement si : ((Stmts)s).ss) {
          ss.append(simpleCE_Stmt(si));
        }
        return ss;
      } else if (s instanceof If) {
        Bexp cond = (Bexp)(((If)s).e);
        Statement body = ((If)s).s;
        Value v = cond.eval(new Env());
        if (v instanceof BoolValue) {
          changed = true;
          if (((BoolValue)v).v) {
            body = simpleCE_Stmt(body); 
            // ! 조건문을 통과하면 body가 deadcode가 아니므로 body에서 deadcode 제거 작업을 수행한다.
            return body;
          } else {
            return new Skip();
            // ! 조건문을 통과하지 못하면 대신에 skip을 넣는다.
          }
        } else {
          body = simpleCE_Stmt(body);
          return new If(cond, body);
        }
      } else if (s instanceof While) {
        Bexp cond = (Bexp)(((While)s).e);
        Statement body = ((While)s).s;
        Value v = cond.eval(new Env());
        if (v instanceof BoolValue) {
          if (((BoolValue)v).v) {
            body = simpleCE_Stmt(body);
            return new While(cond, body);
          } else {
            changed = true;
            return new Skip();
          }
        } else {
          body = simpleCE_Stmt(body);
          return new While(cond, body);
        }
      } else {
        return s;
      }
      // ? deadcode는 왜 if문과 while문만 확인할까? => 조건문이 두 구문에만 존재하기 때문.
    }
  }
}


