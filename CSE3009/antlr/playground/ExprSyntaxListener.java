// Generated from Expr.g4 by ANTLR 4.7.1

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * This class provides an empty implementation of {@link ExprListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
public class ExprSyntaxListener extends ExprBaseListener {
    @Override public void enterProg(ExprParser.ProgContext ctx) {
        System.out.println("Enter prog: " + ctx);
    }
	@Override public void exitProg(ExprParser.ProgContext ctx) {
        System.out.println("Exit prog: " + ctx);
    }
	@Override public void enterExpr(ExprParser.ExprContext ctx) {
        System.out.println("Enter expr: " + ctx);
    }
	@Override public void exitExpr(ExprParser.ExprContext ctx) {
        System.out.println("Exit Expr: " + ctx);
    }
	@Override public void enterEveryRule(ParserRuleContext ctx) {
        System.out.println("Enter rule: " + ctx);
    }
	@Override public void exitEveryRule(ParserRuleContext ctx) {
        System.out.println("Exit rule: " + ctx);
    }
	@Override public void visitTerminal(TerminalNode node) {
        System.out.println("Visit terminal: " + node);
    }
	@Override public void visitErrorNode(ErrorNode node) {
        System.out.println("parsing error: " + node);
    }
}