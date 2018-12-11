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
	 * labeled alternative in {@link WhileParser#var_decls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(WhileParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDecs}
	 * labeled alternative in {@link WhileParser#var_decls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecs(WhileParser.VarDecsContext ctx);
	/**
	 * Visit a parse tree produced by {@link WhileParser#var_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_decl(WhileParser.Var_declContext ctx);
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
	 * Visit a parse tree produced by the {@code If0}
	 * labeled alternative in {@link WhileParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf0(WhileParser.If0Context ctx);
	/**
	 * Visit a parse tree produced by the {@code If}
	 * labeled alternative in {@link WhileParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(WhileParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Skip}
	 * labeled alternative in {@link WhileParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(WhileParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by the {@code While}
	 * labeled alternative in {@link WhileParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(WhileParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Block}
	 * labeled alternative in {@link WhileParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(WhileParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code E_Not}
	 * labeled alternative in {@link WhileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitE_Not(WhileParser.E_NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code E_Bop}
	 * labeled alternative in {@link WhileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitE_Bop(WhileParser.E_BopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code E_Var}
	 * labeled alternative in {@link WhileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitE_Var(WhileParser.E_VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code E_True}
	 * labeled alternative in {@link WhileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitE_True(WhileParser.E_TrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code E_False}
	 * labeled alternative in {@link WhileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitE_False(WhileParser.E_FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code E_Int}
	 * labeled alternative in {@link WhileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitE_Int(WhileParser.E_IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code E_Paren}
	 * labeled alternative in {@link WhileParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitE_Paren(WhileParser.E_ParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expr_single}
	 * labeled alternative in {@link WhileParser#exprs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_single(WhileParser.Expr_singleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expr_list}
	 * labeled alternative in {@link WhileParser#exprs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list(WhileParser.Expr_listContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BOP_AND}
	 * labeled alternative in {@link WhileParser#bop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBOP_AND(WhileParser.BOP_ANDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BOP_OR}
	 * labeled alternative in {@link WhileParser#bop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBOP_OR(WhileParser.BOP_ORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ROP_GT}
	 * labeled alternative in {@link WhileParser#bop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitROP_GT(WhileParser.ROP_GTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ROP_LT}
	 * labeled alternative in {@link WhileParser#bop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitROP_LT(WhileParser.ROP_LTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AOP_PLUS}
	 * labeled alternative in {@link WhileParser#bop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAOP_PLUS(WhileParser.AOP_PLUSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AOP_MINUS}
	 * labeled alternative in {@link WhileParser#bop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAOP_MINUS(WhileParser.AOP_MINUSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AOP_MULT}
	 * labeled alternative in {@link WhileParser#bop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAOP_MULT(WhileParser.AOP_MULTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AOP_DIV}
	 * labeled alternative in {@link WhileParser#bop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAOP_DIV(WhileParser.AOP_DIVContext ctx);
}