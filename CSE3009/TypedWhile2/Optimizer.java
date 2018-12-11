class Optimizer {
    static boolean changed = false;

    static boolean opt_ce = false;
    static boolean opt_eb = false;
    static boolean opt_rs = false;
    static boolean opt_cf = false;

    AstWithEval run(AstWithEval ast) {
        DeadCode dc = new DeadCode();
        ConstantFolding cf = new ConstantFolding();
        RemoveEmptyBlock eb = new RemoveEmptyBlock();
        RemoveSkip rs = new RemoveSkip();

        do {
            changed = false; 

            if (opt_ce) {
                ast = dc.run(ast);
            }
            if (opt_eb) {
                ast = eb.run(ast);
            }
            if (opt_rs) {
                ast = rs.run(ast);
            }
            if (opt_cf) {
                ast = cf.run(ast);
            }
        } while (changed);

        return ast;
    }

    class Simplifier {
        AstWithEval run(AstWithEval ast) {
            ast.s = simple(ast.s);
            return ast;
        }

        Statement simple(Statement s) {
            return s;
        }
    }
    
    class DeadCode extends Simplifier {
        Statement simple(Statement s) {
            if (s instanceof Stmts) {
                Stmts result = new Stmts();
                for (Statement st : ((Stmts)s).ss) {
                    result.append(simple(st));
                }
                return result;
            } else if (s instanceof If) {
                Bexp cond = (Bexp)(((If)s).e);
                Statement body = ((If)s).s;
                Value v = cond.eval(new Env());
                if (v instanceof BoolValue) {
                    changed = true;
                    if (((BoolValue)v).v) {
                        body = simple(body);
                        return body;
                    } else {
                        return new Skip();
                    }
                } else {
                    body = simple(body);
                    return new If(cond, body);
                }
            } else if (s instanceof While) {
                Bexp cond = (Bexp)(((While)s).e);
                Statement body = ((While)s).s;
                Value v = cond.eval(new Env());
                if (v instanceof BoolValue) {
                    if (((BoolValue)v).v) {
                        body = simple(body);
                        return new While(cond, body);
                    } else {
                        changed = true;
                        return new Skip();
                    }
                } else {
                    body = simple(body);
                    return new While(cond, body);
                }
            } else {
                return s;
            }
        }
    }
    
    class RemoveSkip extends Simplifier {
        Statement simple (Statement s) {
            if (s instanceof Stmts) {
                Stmts result = new Stmts();
                for (Statement st : ((Stmts)s).ss) {
                    st = simple(st);
                    if (st instanceof Skip) {
                        changed = true;
                    } else {
                        result.append(st);
                    }
                }
                return result;
            } else if (s instanceof If) {
                Bexp cond = (Bexp)(((If)s).e);
                Statement body = ((If)s).s;
                body = simple(body);
                if (body instanceof Skip) {
                    changed = true;
                    return new Skip();
                } else {
                    return new If(cond, body);
                }
            } else if (s instanceof While) {
                Bexp cond = (Bexp)(((While)s).e);
                Statement body = ((While)s).s;
                body = simple(body);
                return new While(cond, body);
            } else {
                return s;
            }
        }
    }
    
    class RemoveEmptyBlock extends Simplifier {
        Statement simple(Statement s) {
            if (s instanceof Stmts) {
                Stmts result = new Stmts();
                for (Statement st : ((Stmts)s).ss) {
                    result.append(simple(st));
                }
                if ((result.ss).isEmpty()) {
                    changed = true;
                    return new Skip();
                } else {
                    return result;
                }
            } else if (s instanceof If) {
                Bexp cond = (Bexp)(((If)s).e);
                Statement body = ((If)s).s;
                body = simple(body);
                return new If(cond, body);
            } else if (s instanceof While) {
                Bexp cond = (Bexp)(((While)s).e);
                Statement body = ((While)s).s;
                body = simple(body);
                return new While(cond, body);
            } else {
                return s;
            }
        } 
    }
    
    class ConstantFolding extends Simplifier {
        Statement simple(Statement s) {
            if (s instanceof Stmts) {
                Stmts result = new Stmts();
                for (Statement st : ((Stmts)s).ss) {
                    result.append(simple(st));
                }
                return result;
            } else if (s instanceof Assign) {
                Variable lhs = ((Assign)s).lhs;
                Expression rhs = ((Assign)s).rhs;
                return new Assign(lhs, cfExpr(rhs));
            } else if (s instanceof Print) {
                Expression e = ((Print)s).e;
                return new Print(cfExpr(e));
            } else if (s instanceof If) {
                Bexp cond = (Bexp)(((If)s).e);
                Statement body = ((If)s).s;
                return new If(((Bexp)cfExpr(cond)), body);
            } else if (s instanceof While) {
                Bexp cond = (Bexp)(((While)s).e);
                Statement body = ((While)s).s;
                return new While(((Bexp)cfExpr(cond)), body);
            } else {
                return s;
            }
        }

        Expression cfExpr(Expression e) {
            if (e instanceof Aexp) {
                return cfAexp((Aexp)e);
            } else if (e instanceof Bexp) {
                return cfBexp((Bexp)e);       
            } else {
                return e;
            }
        }

        Aexp cfAexp(Aexp e) {
            Value v = e.eval(new Env());
            if (v instanceof IntValue) {
                return new AEint(((IntValue)v).v);
            } else {
                return e;
            }
        }

        Bexp cfBexp(Bexp e) {
            Value v = e.eval(new Env());
            if (v instanceof BoolValue) {
                if (((BoolValue)v).v) {
                    return new BEtrue();
                } else {
                    return new BEfalse();
                }
            } else {
                return e;
            }
        }
    }
}
