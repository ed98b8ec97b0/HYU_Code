import java.util.*;

public class AstWithEval extends Node {
  /*
  코드 전체를 처음 받아오는 클래스.
  코드가 들어오면 이를 declaration 부분와 statement 부분 둘로 나누어서 각각을 타입 검사한다.
   */
  Declaration d;
  Statement s;

  AstWithEval(Declaration d, Statement s) {
    this.d = d; this.s = s;
  }

  Env execute() {
    /* 
    우선 Declaration 부분을 실행한다.
    이때, Declaration은 타입 지정에 해당하기 때문에 TypeEnv을 통해 변수와 타입을 지정한다.
    Statement는 Delaration에서 지정한 변수들을 이용하여 작동하기때문에 환경을 분리하기 위해서 TypeEnv가 아닌 Env를 사용한다.
    ? Env에서 두가지 형태로 나눌 순 없나? Type을 관리하는 map과 계산 환경을 처리하기 위한 map 두가지를 사용하고 그 뒤에 오버로딩으로 구분할 수 있지 않나?
     */
    TypeEnv te = d.execute(new TypeEnv());
    Env env = new Env(); 
    return s.execute(env); // ? 그냥 이부분은 s.execute(new Env())로 해도 되지않을까?
  }

  public String toString() {
    return d.toString() + ";\n" + s.toString();
  }

  void print() {
    System.out.println("- print code ----------------------------------");
    System.out.println(d.toString() + ";");
    s.print(0);
    System.out.println("\n-----------------------------------------------");
  }
}

class Variable extends Node {
  // 변수 선언 시, 사용되는 class
  // 타입을 지정해주지 않으면 기본값으로 void 타입이 배정된다.
  // 이 class를 이용해서 보다 안전하고 간단하게 ID, Type을 전달할 수 있게 된다.
  String name;
  Type t;

  public Variable() {
    this("", new VoidType());
  }

  public Variable(String name) {
    this(name, new VoidType());
  }

  public Variable(String name, Type t) {
    this.name = name;
    this.t = t;
  }

  public String toString() {
    return name + ":" + t;
  }
}

class Declaration extends Node {
  // Declaration 코드 부분을 처리하는 클래스.
  // 어차피 이 클래스는 밑에서 작성할 vector의 제네럴 타입으로 쓰기 위해 만들어졌다.
  TypeEnv execute(TypeEnv te) {
    return te;
  }
};

class Decls extends Declaration {
  // 여러개의 Declaration을 처리하여 각각 하나의 declaration으로 처리하기 위한 클래스.
  Vector<Declaration> ds; // Declaration들을 담는 벡터.
  //? 왜 벡터일까? => Java에서 가변 길이의 배열이 곧 벡터이기 때문!
  Decls() {
    ds = new Vector<Declaration>();
  }

  Decls append(Declaration d) {
  // Declaration들을 벡터 안에 넣는 함수.
    ds.add(d); return this;
  }

  TypeEnv execute(TypeEnv te) {
    // 벡터 안에 있는 각각의 Delaration마다 실행시켜준다.
    // 그리고 그 결과물을 다시 반환.
    for (Declaration d : ds) {
      te = d.execute(te);
    }
    return te;
  }

  public String toString() {
    String s = "";
    boolean fst = true;
    for (Declaration d : ds) {
      if (fst) {
        fst = false;
      } else {
        s += ";\n";
      }
      s += d.toString();
    }
    return s;
  }
}

class VarDecl extends Declaration {
  // 실제 Declaration이 돌아가는 부분.
  // TypeEnv에 ID와 Type을 넣어 향후에 사용할 때, 쉽게 쓸 수 있도록 한다.
  Variable v;
  VarDecl(Variable v) {
    this.v = v;
  }

  TypeEnv execute(TypeEnv te) {
    // VarDecl이 하는 일은 ID : Type_exp로 구성된 라인을 보고 집합 안에 ID와 Type으로 이루어진 쌍을 넣는다.
    // 따라서, execute를 할 때, 주어진 변수를 보고 TypeEnv에 넣는 것.
    return te.insert(this.v.name, this.v.t);
  }

  public String toString() {
    return v.toString();
  }
}

class Statement extends Node {
  // 향후 Statement에 관련된 class를 쓰기 위해 만든 class.
  Env execute(Env env) {
    return env;
  }

  void print(int tab) {
    Util.print_tab(tab);
    System.out.print(this.toString());
  }
};

class Stmts extends Statement {
  // 여러개의 Statement들을 관리하고 실행하기 위한 class.
  Vector<Statement> ss;
  Stmts() {
    ss = new Vector<Statement>();
  }

  Stmts append(Statement s) {
    ss.add(s);
    return this;
  }

  Env execute(Env env) {
    for (Statement s : this.ss) {
      env = s.execute(env);
      // ? 이러면 env가 계속해서 업데이트되진 않을까? => 업데이트되어도 상관없다. 환경에 들어가거나 빠지는거뿐. 그 외의 경우에도 env가 그대로 return되기만 한다.
    }
    return env;
  }

  public String toString() {
    String s = "";
    boolean fst = true;
    for (Statement i : this.ss) {
      if (fst) {
        fst = false;
      } else {
        s += ";\n";
      }
      s += i;
    }
    return s;
  }

  void print(int tab) {
    boolean fst = true;
    for (Statement s : ss) {
      if (fst) {
        fst = false;
      } else {
        System.out.println(";");
      }
      s.print(tab);
    }
  }
}

class Assign extends Statement {
  // TypeEnv에서 온 lhs를 이용해서 rhs로 얻어낸 값의 쌍을 Env에 넣는 것.
  Variable lhs; /* = */ Expression rhs;
  Assign(Variable lhs, Expression rhs) {
    this.lhs = lhs; this.rhs = rhs;
  }

  Env execute(Env env) {
    return env.insert(lhs.name, rhs.eval(env));
    // ? 어째서 rhs.eval(env)일까? => 뒤에서 Expression에 overiding을 수행한다.
  }

  public String toString() {
    return lhs.toString() + " = " + rhs.toString();
  }
}

class Print extends Statement {
  // 주어진 Expression을 system output으로 출력하는 class.
  /*print*/ Expression e;
  Print(Expression e) { this.e = e; }

  Env execute(Env env) {
    Value v = e.eval(env);
    System.out.println(v.toString());
    return env;
  }

  public String toString() {
    return "print (" + e.toString() +")";
  }
}

class Skip extends Statement {

  public String toString() {
    return "skip";
  }

  Env execute(Env env) {
    return env;
  }
}

class If extends Statement {
  /*if*/ Expression e; /*then*/ Statement s;
  If(Expression e, Statement s) {
    this.e = e; this.s = s;
  }

  Env execute(Env env) {
    // 1. Expression의 Value를 가져온다.
    // 2. (제대로 됐다면) BoolValue가 반환됨.
    // 3. BoolValue의 값이 true면 Statement를 실행하고 false면 아무일도 하지 않는다.
    Value e = this.e.eval(env);
    if (e instanceof BoolValue) {
      if (((BoolValue)e).v) {
        return this.s.execute(env);
      } else {
        return env;
      }
    } else {
      System.out.println("unexpected type of value : " + e);
      return env;
    }
  }

  public String toString() {
    return "if (" + e.toString() + ") then\n" +
      s.toString();
  }

  void print(int tab) {
    Util.print_tab(tab);
    System.out.println("if (" + e + ") then");
    s.print(tab + 1);
  }
}

class While extends Statement {
  /*while*/ Expression e; /*do*/ Statement s /*od*/;
  While(Expression e, Statement s) {
    this.e = e; this.s = s;
  }

  // [[While(e,s)]] =>  [[ If(e, {s;While(e,s)}) ]]
  // Env execute(Env env) {
  //   Stmts block = new Stmts();
  //   block.append(this.s).append(this);
  //   Statement _if = new If(this.e, block);
  //   _if.excute(env);
  // }

  Env execute(Env env) {
    // 1. Expression을 Value로 부터 가져온다.
    // 2. 값이 BoolValue면 계속 진행, 아니면 그냥 넘어간다.
    // 3. true면 Statement를 실행한다. 그 후, 돌아오는 환경을 다시 실행한다.
    // ! this.execute를 사용해야 순환이 된다. env가 그대로 다시 execute(env)로 들어가서 재귀 구조가 형성됨.
    Value e = this.e.eval(env);
    if (e instanceof BoolValue) {
      if (((BoolValue)e).v) {
        return this.execute(this.s.execute(env));
      } else {
        return env;
      }
    } else {
      System.out.println("unexpected type of value : " + e);
      return env;
    }
  }

  public String toString() {
    return "while (" + e.toString() + ") do\n" +
      s.toString() + "od\n";
  }

  void print(int tab) {
    Util.print_tab(tab);
    System.out.println("while (" + e + ") do");
    s.print(tab + 1);
    Util.print_tab(tab);
    System.out.println("od");
  }
}

class Expression extends Node {
  // 향후 사용할 모든 Expression에서 사용하기 위한 class.
  Value eval(Env env) {
    return new NullValue();
  }

  Expression fromValue(Value v) {
    // Value 값을 보고 그걸 expression으로 내놓는 함수.
    if (v instanceof IntValue) {
      Expression e = new AEint(((IntValue)v).v);
      return e;
    } else if (v instanceof BoolValue) {
      if (((BoolValue)v).v) {
        Expression e = new BEtrue();
        return e;
      } else {
        Expression e = new BEfalse();
        return e;
      }
    } else {
      return this;
    }
  }
};

class Aexp extends Expression {};
class AEop extends Aexp {
  Aexp e1; Aop op; Aexp e2;
  AEop(Aexp e1, Aop op, Aexp e2) {
    this.e1 = e1; this.op = op; this.e2 = e2;
  }

  Value eval(Env env) {
    // operation에 따라 그 값을 계산해서 Value로 넘겨줌.
    Value v1 = this.e1.eval(env);
    Value v2 = this.e2.eval(env);
    if (v1 instanceof IntValue && v2 instanceof IntValue) {
      if (this.op instanceof AopPLUS) {
        return new IntValue(((IntValue)v1).v + ((IntValue)v2).v);
      } else if (this.op instanceof AopMINUS) {
        return new IntValue(((IntValue)v1).v - ((IntValue)v2).v);
      } else if (this.op instanceof AopMULT) {
        return new IntValue(((IntValue)v1).v * ((IntValue)v2).v);
      } else if (this.op instanceof AopDIV) {
        return new IntValue(((IntValue)v1).v / ((IntValue)v2).v);
      } else {
        return new NullValue();
      }
    } else {
      System.out.println("Unexpected type of values : " + v1 + "," + v2);
      return new NullValue();
    }
  }

  public String toString() {
    return e1.toString() + " " + op.toString() + " " + e2.toString();
  }
};
class AEint extends Aexp {
  int num;
  AEint(int num) { this.num = num; }

  Value eval(Env env) {
    return new IntValue(num);
  }

  public String toString() {
    return ""+num;
  }
}
class AEid extends Aexp {
  Variable v;
  AEid(Variable v) { this.v = v; }

  Value eval(Env env) {
    // Env class에 내장되어있는 것을 사용. value값을 가져온다.
    // 왜냐면 Variable에서는 Type만 저장하기 때문.
    return env.lookup(v.name);
  }

  public String toString() {
    return v.toString();
  }
}

class Bexp extends Expression {
  Value eval(Env env) {
    return new NullValue();
  }
};
class BEop extends Bexp {
  Bexp e1; Bop op; Bexp e2;
  BEop(Bexp e1, Bop op, Bexp e2) {
    this.e1 = e1; this.op = op; this.e2 = e2;
  }

  Value eval(Env env) {
    // 마찬가지로 operation에 따라 계산해서 value값으로 넘겨주는 함수.
    // 문법 추가 시 이 부분에 추가를 해야함.
    Value v1 = this.e1.eval(env);
    Value v2 = this.e2.eval(env);
    if (v1 instanceof BoolValue && v2 instanceof BoolValue) {
      if (this.op instanceof BopAND) {
        return new BoolValue(((BoolValue)v1).v && ((BoolValue)v2).v);
      } else if (this.op instanceof BopOR) {
        return new BoolValue(((BoolValue)v1).v || ((BoolValue)v2).v);
      } else {
        return new NullValue();
      }
    } else {
      System.out.println("Unexpected type of values : " + v1 + "," + v2);
      return new NullValue();
    }
  }

  public String toString() {
    return e1.toString() + " " + op.toString() + " " + e2.toString();
  }
}
class BErop extends Bexp {
  Aexp e1; Rop op; Aexp e2;
  BErop(Aexp e1, Rop op, Aexp e2) {
    this.e1 = e1; this.op = op; this.e2 = e2;
  }

  Value eval(Env env) {
    // 만약에 문법을 추가하면 이곳에다가 새로 추가해야한다.
    Value v1 = this.e1.eval(env);
    Value v2 = this.e2.eval(env);
    if (v1 instanceof IntValue && v2 instanceof IntValue) {
      if (this.op instanceof RopGT) {
        return new BoolValue(((IntValue)v1).v > ((IntValue)v2).v);
      } else if (this.op instanceof RopLT) {
        return new BoolValue(((IntValue)v1).v < ((IntValue)v2).v);
      } else {
        return new NullValue();
      }
    } else {
      System.out.println("Unexpected type of values : " + v1 + "," + v2);
      return new NullValue();
    }
  }

  public String toString() {
    return e1.toString() + " " + op.toString() + " " + e2.toString();
  }
}
class BEtrue extends Bexp {
  Value eval(Env env) {
    return new BoolValue(true);
  }
  public String toString() {
    return "true";
  }
}
class BEfalse extends Bexp {
  Value eval(Env env) {
    return new BoolValue(false);
  }
  public String toString() {
    return "false";
  }
}
class BEnot extends Bexp {
  Bexp b;
  BEnot(Bexp b) { this.b = b; }

  Value eval(Env env) {
    Value v = this.b.eval(env);
    if (v instanceof BoolValue) {
      return ((BoolValue)v).negation();
    } else {
      return new NullValue();
    }
  }

  public String toString() {
    return "!(" + b.toString()+")";
  }
}
class BEid extends Bexp {
  Variable v;
  BEid(Variable v) { this.v = v; }

  Value eval(Env env) {
    // Env class에 내장되어있는 것을 사용. value값을 가져온다.
    // 왜냐면 Variable에서는 Type만 저장하기 때문.
    return env.lookup(v.name);
  }

  public String toString() {
    return v.toString();
  }
}

class Aop extends Node {};
class AopPLUS extends Aop {
  public String toString() {
    return "+";
  }
};
class AopMINUS extends Aop {
  public String toString() {
    return "-";
  }
};
class AopMULT extends Aop {
  public String toString() {
    return "*";
  }
};
class AopDIV extends Aop {
  public String toString() {
    return "/";
  }
};
class Bop extends Node {};
class BopAND extends Bop {
  public String toString() {
    return "&";
  }
};
class BopOR extends Bop {
  public String toString() {
    return "|";
  }
};
class Rop extends Node {};
class RopGT extends Rop {
  public String toString() {
    return ">";
  }
};
class RopLT extends Rop {
  public String toString() {
    return "<";
  }
};