// Generated from Expr.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class EvalVisitor extends ExprBaseVisitor<Integer>{
	@Override public Integer visitProg(ExprParser.ProgContext ctx) {
		return visit(ctx.expr(0));
	}
	@Override public Integer visitADD(ExprParser.ADDContext ctx) {
		System.out.println("Visit ADD: " + ctx);
		int left = visit(ctx.expr(0));
		int right = visit(ctx.expr(1));
		if (ctx.ADD().getText().equals("+")) {
			System.out.println("Result: " + (left + right));
			return left + right;
		} else {
			System.out.println("Result: " + (left - right));
			return left - right;
		}
	}
	@Override public Integer visitMULTI(ExprParser.MULTIContext ctx) {
		System.out.println("Visit MULIT: " + ctx);
		int left = visit(ctx.expr(0));
		int right = visit(ctx.expr(1));
		if (ctx.MULTI().getText().equals("*")) {
			System.out.println("Result: " + (left * right));
			return left * right;
		} else {
			System.out.println("Result: " + (left / right));
			return left / right;
		}
	}
	@Override public Integer visitINT(ExprParser.INTContext ctx) {
		return Integer.valueOf(ctx.INT().getText());
	}
	@Override public Integer visitBARC(ExprParser.BARCContext ctx) {
		return visit(ctx.expr());
	}
}