import java.util.*;

class Checker {
///// 2.1
/////
///// Type checker
/////
  // 타입 검사를 수행합니다.
  boolean type_checker(Ast ast) {
      TypeEnv te = type_checker_decl(ast.d);
      if (type_checker_stmt(ast.s, te)) {
        return true;
      } else {
        return false;
      }
  }

  TypeEnv type_checker_decl(Declaration d) {
    TypeEnv te = new TypeEnv();
    if (d instanceof Decls) {
      for (Declaration de : ((Decls)d).ds) {
        de.execute(te);
      }
    } else if (d instanceof VarDecl) {
      d.execute(te);
    }
    return te;
  }

  boolean type_checker_stmt(Statement s, TypeEnv te) {
    if (s instanceof Stmts) {
      for (Statement st : ((Stmts)s).ss) {
        if (!type_checker_stmt(st, te)) {
          return false;
        }
      }
    } else if (s instanceof Assign) {
      Variable lhs = ((Assign)s).lhs;
      Expr rhs = ((Assign)s).rhs;
      if (type_checker_expr(rhs, te)) {
        Type t = te.lookup(lhs.name);
        Value v = rhs.fromValue();
        
      } else {
        return false;
      }
    } else if (s instanceof Print) { 
      return type_checker_expr(((Print)s).e, te);
    } else if (s instanceof If0) {
      return type_checker_expr(((If0)s).e, te) && type_checker_stmt(((If0)s).s, te);
    } else if (s instanceof If) {
      return type_checker_expr(((If)s).e, te) && type_checker_stmt(((If)s).st, te) && type_checker_stmt(((If)s).sf, te);
    } else if (s instanceof While) {
      return type_checker_expr(((While)s).e, te) && type_checker_stmt(((While)s).s, te);
    } else {
      return true;
    }
    return true;
  }

  boolean type_checker_expr(Expr e, TypeEnv te) {
    if (e instanceof E_Bop) {
      if (type_checker_expr(((E_Bop)e).e1, te) && type_checker_expr(((E_Bop)e).e2, te)) {
        if (((E_Bop)e).e1.eval(new Env()) instanceof IntValue && ((E_Bop)e).e2.eval(new Env()) instanceof IntValue) {
          return true;
        } else if (((E_Bop)e).e1.eval(new Env()) instanceof BoolValue && ((E_Bop)e).e2.eval(new Env()) instanceof BoolValue) {
          return true;
        } else if (((E_Bop)e).e1 instanceof E_Var && ((E_Bop)e).e2 instanceof E_Var) {
            return true;
        } else {
          System.out.println("[E_ELS] " + ((E_Bop)e).e1.toString() + ", " + ((E_Bop)e).e2.toString());
          return false;
        } 
      } else {
        return false;
      }
    } else if (e instanceof E_Not) {
      return type_checker_expr(((E_Not)e).e, te);
    } else {
      return true;
    }
  }

///// 2.1
/////
///// Declared variables
/////
  // 프로그램에서 선언만하고 사용되지 않은 변수의 집합을 모읍니다.
  HashSet declared_variables(Ast ast) {
    HashSet <String> h = declared_variables_decl(ast.d);
    return h;
  }

  // Declared variables - declared_variables_variables_decl
  // 선언된 변수의 집합을 모읍니다.
  HashSet declared_variables_decl(Declaration d) {
    HashSet <String> h = new HashSet();
    for (Declaration de : ((Decls)d).ds) {
      h.add(((VarDecl)de).v.name);
    }
    return new HashSet();
  }

///// 2.2
/////
///// Used variables
/////
  // 프로그램에서 사용한 변수의 집합을 모읍니다.
  HashSet used_variables(Ast ast) {
    return used_variables_stmt(ast.s, new HashSet());
  }

  // Used variables - declared_variables_variables_decl
  // 선언된 변수의 집합을 모읍니다.
  // Used variables - used_variables_stmt
  HashSet used_variables_stmt(Statement s, HashSet vars) {
    if (s instanceof Stmts) {
      for (Statement st : ((Stmts)s).ss) {
        vars.addAll(used_variables_stmt(st, vars));
      }
      return vars;
    } else if (s instanceof Assign) {
      vars.add(((Assign)s).lhs.name);
      vars.addAll(used_variables_expr(((Assign)s).rhs, vars));
      return vars;
    } else if (s instanceof Print) {
      vars.addAll(used_variables_expr(((Assign)s).rhs, vars));
      return vars;
    } else if (s instanceof If0) { 
      vars.addAll(used_variables_expr(((If0)s).e, vars));
      vars.addAll(used_variables_stmt(((If0)s).s, vars));
      return vars;
    } else if (s instanceof If) {
      vars.addAll(used_variables_expr(((If)s).e, vars));
      vars.addAll(used_variables_stmt(((If)s).sf, vars));
      vars.addAll(used_variables_stmt(((If)s).st, vars));
      return vars;
    } else if (s instanceof While) { 
      vars.addAll(used_variables_expr(((While)s).e, vars));
      vars.addAll(used_variables_stmt(((While)s).s, vars));
      return vars;
    } else {
      return vars;
    }
  }

  // Used variables - used_variables_expr
  HashSet used_variables_expr(Expr e, HashSet vars) {
    if (e instanceof E_Bop) {
      vars.addAll(used_variables_expr(((E_Bop)e).e1, vars));
      vars.addAll(used_variables_expr(((E_Bop)e).e2, vars));
      return vars;
    } else if (e instanceof E_Var) {
      vars.add(((E_Var)e).v.name);
      return vars;
    } else if (e instanceof E_Not) {
      vars.addAll(used_variables_expr(((E_Not)e).e, vars));
      return vars;
    }
    return vars;
  }

///// 2.3
/////
///// use of undefined variables
/////
  // 프로그램에서 선언만하고 사용되지 않은 변수가 있는지 확인합니다.
  boolean use_of_undefined_variables(Ast ast) {
    return false;
  }

  // ...
}