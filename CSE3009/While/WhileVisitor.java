// Generated from While.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link WhileParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface WhileVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link WhileParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(WhileParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDec}
	 * labeled alternative in {@link WhileParser#var_decs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(WhileParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDecs}
	 * labeled alternative in {@link WhileParser#var_decs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecs(WhileParser.VarDecsContext ctx);
	/**
	 * Visit a parse tree produced by {@link WhileParser#var_dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_dec(WhileParser.Var_decContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntType}
	 * labeled alternative in {@link WhileParser#type_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(WhileParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolType}
	 * labeled alternative in {@link WhileParser#type_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolType(WhileParser.BoolTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrType}
	 * labeled alternative in {@link WhileParser#type_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrType(WhileParser.ArrTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Stmt_list}
	 * labeled alternative in {@link WhileParser#stmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt_list(WhileParser.Stmt_listContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Stmt_single}
	 * labeled alternative in {@link WhileParser#stmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt_single(WhileParser.Stmt_singleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link WhileParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(WhileParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Print}
	 * labeled alternative in {@link WhileParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(WhileParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If}
	 * labeled alternative in {@link WhileParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(WhileParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link WhileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(WhileParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link WhileParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(WhileParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link WhileParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(WhileParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link WhileParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(WhileParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryOp}
	 * labeled alternative in {@link WhileParser#aexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOp(WhileParser.BinaryOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BEFalse}
	 * labeled alternative in {@link WhileParser#bexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBEFalse(WhileParser.BEFalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BENot}
	 * labeled alternative in {@link WhileParser#bexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBENot(WhileParser.BENotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BERop}
	 * labeled alternative in {@link WhileParser#bexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBERop(WhileParser.BERopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BEBop}
	 * labeled alternative in {@link WhileParser#bexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBEBop(WhileParser.BEBopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BETrue}
	 * labeled alternative in {@link WhileParser#bexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBETrue(WhileParser.BETrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RopGT}
	 * labeled alternative in {@link WhileParser#rop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRopGT(WhileParser.RopGTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RopLT}
	 * labeled alternative in {@link WhileParser#rop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRopLT(WhileParser.RopLTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BopAND}
	 * labeled alternative in {@link WhileParser#bop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBopAND(WhileParser.BopANDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BopOR}
	 * labeled alternative in {@link WhileParser#bop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBopOR(WhileParser.BopORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link WhileParser#aop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(WhileParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link WhileParser#aop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(WhileParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Mult}
	 * labeled alternative in {@link WhileParser#aop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(WhileParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Div}
	 * labeled alternative in {@link WhileParser#aop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(WhileParser.DivContext ctx);
}