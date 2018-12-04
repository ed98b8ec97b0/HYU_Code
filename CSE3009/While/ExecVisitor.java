// Generated from While.g4 by ANTLR 4.7.1
import java.util.Dictionary;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class ExecVisitor extends WhileBaseVisitor<Pair<Integer, Type>> {

  @Override public Pari<Integer, Type> visitProg(WhileParser.ProgContext ctx) {
    visit(ctx.var_decs());
    visit(ctx.stmts());
    return new Pair<>(0, new VoidType());
  }

  @Override public Pair<Integer, Type> visitVarDecs(WhileParser.VarDecsContext ctx) {
    visit(ctx.var_decs());
    visit(ctx.var_dec());
    return new Pair<>(0, new VoidType());
  }

  @Override public Pair<Integer, Type> visitVarDec(WhileParser.VarDecContext ctx) {
    visit(ctx.var_dec());
    return new Pair<>(0, new VoidType());
  }

  @Override public Pair<Integer, Type> visitVar_dec(WhileParser.Var_decContext ctx) {
    return new Pair<>(0, new VoidType());
  }

  @Override public Pair<Integer, Type> visitIntType(WhileParser.IntTypeContext ctx) {
    TypeEnv.insert(ctx.ID().getText(), visit(ctx.type_exp()).getValue());
    return new Pair<>(0, IntType);
  }

  @Override public Pair<Integer, Type> visitBoolType(WhileParser.BoolTypeContext ctx) {
    return new Pair<>(0, BoolType);
  }

  @Override public Pair<Integer, Type> visitArrType(WhileParser.ArrTypeContext ctx) {
    return new Pair<>(0, ArrType(Integer.valueof(ctx.INT().getText()), visit(ctx.type_exp()).getValue()));
  }

  @Override public Pair<Integer, Type> visitStmt_list(WhileParser.Stmt_listContext ctx) {
    visit(ctx.stmts());
    visit(ctx.stmt());
    return new Pair<>(0, VoidType);
  }

  @Override public Pair<Integer, Type> visitStmt_single(WhileParser.Stmt_singleContext ctx) {
    visit(ctx.stmt());
    return new Pair<>(0, VoidType);
  }

  @Override public Pair<Integer, Type> visitAssign(WhileParser.AssignContext ctx) {
    Env.insert(ctx.ID().getText(), visit(ctx.expr()).getKey());
    return new Pair<>(0, VoidType);
  }

  @Override public Pair<Integer, Type> visitPrint(WhileParser.PrintContext ctx) {
    System.out.println(visit(ctx.aexp()).getKey());
    return new Pair<>(0, VoidType);
  }

  @Override public Pair<Integer, Type> visitIf(WhileParser.IfContext ctx) {
    if (visit(ctx.bexp()).getKey() != 0) {
      visit(ctx.stmt());
    }
    return new Pair<>(0, VoidType);
  }

  @Override public Pair<Integer, Type> visitVar(WhileParser.VarContext ctx) {
    String id = ctx.ID().getText();
    return new Pair<>(Env.lookup(id), TypeEnv.lookup(id));
  }

  @Override public Pair<Integer, Type> visitParens(WhileParser.ParensContext ctx) {
    return visit(ctx.aexp());
  }

  @Override public Pair<Integer, Type> visitInt(WhileParser.IntContext ctx) {
    return new Pair<>(Integer.valueOf(ctx.INT().getText()), IntType);
  }

  @Override public Pair<Integer, Type> visitBinaryOp(WhileParser.BinaryOpContext ctx) {
    int left = visit(ctx.aexp(0)).getKey();
    int right = visit(ctx.aexp(1)).getKey();
    switch (visit(ctx.aop()).getKey()) {
      case 0: return new Pair<>(left + right, new IntType());
      case 1: return new Pair<>(left - right, new IntType());
      case 2: return new Pair<>(left * right, new IntType());
      default: return new Pair<>(left / right, new IntType());
    }
  }

  @Override public Pair<Integer, Type> visitPlus(WhileParser.PlusContext ctx) {
    return new Pair<>(0, VoidType);
  }

  @Override public Pair<Integer, Type> visitMinus(WhileParser.MinusContext ctx) {
    return new Pair<>(1, VoidType);
  }

  @Override public Pair<Integer, Type> visitMult(WhileParser.MultContext ctx) {
    return new Pair<>(2, VoidType);
  }

  @Override public Pair<Integer, Type> visitDiv(WhileParser.DivContext ctx) {
    return new Pair<>(3, VoidType);
  }

  @Override public Pair<Integer, Type> visitBETrue (WhileParser.BETrueContext ctx) {
    return new Pair<>(1, VoidType);
  }

  @Override public Pair<Integer, Type> visitBEFalse (WhileParser.BEFalseContext ctx) {
    return new Pair<>(0, VoidType);
  }

  @Override public Pair<Integer, Type> visitBENot (WhileParser.BENotContext ctx) {
    if (visit(ctx.bexp()).getKey() == 0) {
      return new Pair<>(1, VoidType);
    } else {
      return new Pair<>(0, VoidType);
    }
  }

  @Override public Pair<Integer, Type> visitBERop (WhileParser.BERopContext ctx) {
    int left = visit(ctx.aexp(0)).getKey();
    int right = visit(ctx.aexp(1)).getKey();
    boolean b;
    switch (visit(ctx.rop()).getKey()) {
      case 0: b = left > right; break;
      default: b =  left < right; break;
    }
    if (b)
      return new Pair<>(1, VoidType);
    else
      return new Pair<>(0, VoidType);
  }

  @Override public Pair<Integer, Type> visitBEBop (WhileParser.BEBopContext ctx) {
    int left = visit(ctx.bexp(0)).getKey();
    int right = visit(ctx.bexp(1)).getKey();
    switch (visit(ctx.bop()).getKey()) {
      case 0: return new Pair<>(left * right, VoidType);
      default: return new Pair<>(left + right, VoidType);
    }
  }

  @Override public Pair<Integer, Type> visitRopGT (WhileParser.RopGTContext ctx) {
    return new Pair<>(0, VoidType);
  }

  @Override public Pair<Integer, Type> visitRopLT (WhileParser.RopLTContext ctx) {
    return new Pair<>(1, VoidType);
  }

  @Override public Pair<Integer, Type> visitBopAND (WhileParser.BopANDContext ctx) {
    return new Pair<>(0, VoidType);
  }

  @Override public Pair<Integer, Type> visitBopOR (WhileParser.BopORContext ctx) {
    return new Pair<>(0, VoidType);
  }
}