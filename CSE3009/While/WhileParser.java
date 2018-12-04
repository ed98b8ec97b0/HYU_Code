// Generated from While.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WhileParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, NEWLINE=17, 
		INT=18, ID=19, SEMI=20, MULT=21, PLUS=22, DIV=23, MINUS=24, GT=25, LT=26, 
		AND=27, OR=28;
	public static final int
		RULE_prog = 0, RULE_var_decs = 1, RULE_var_dec = 2, RULE_type_exp = 3, 
		RULE_stmts = 4, RULE_stmt = 5, RULE_expr = 6, RULE_aexp = 7, RULE_bexp = 8, 
		RULE_rop = 9, RULE_bop = 10, RULE_aop = 11;
	public static final String[] ruleNames = {
		"prog", "var_decs", "var_dec", "type_exp", "stmts", "stmt", "expr", "aexp", 
		"bexp", "rop", "bop", "aop"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':'", "'int'", "'bool'", "'array'", "'['", "']'", "'of'", "'='", 
		"'print'", "'if'", "'then'", "'('", "')'", "'true'", "'false'", "'!'", 
		null, null, null, "';'", "'*'", "'+'", "'/'", "'-'", "'>'", "'<'", "'&'", 
		"'|'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "NEWLINE", "INT", "ID", "SEMI", "MULT", 
		"PLUS", "DIV", "MINUS", "GT", "LT", "AND", "OR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "While.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public WhileParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public Var_decsContext var_decs() {
			return getRuleContext(Var_decsContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(WhileParser.SEMI, 0); }
		public StmtsContext stmts() {
			return getRuleContext(StmtsContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			var_decs(0);
			setState(25);
			match(SEMI);
			setState(26);
			stmts(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_decsContext extends ParserRuleContext {
		public Var_decsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decs; }
	 
		public Var_decsContext() { }
		public void copyFrom(Var_decsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarDecContext extends Var_decsContext {
		public Var_decContext var_dec() {
			return getRuleContext(Var_decContext.class,0);
		}
		public VarDecContext(Var_decsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitVarDec(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarDecsContext extends Var_decsContext {
		public Var_decsContext var_decs() {
			return getRuleContext(Var_decsContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(WhileParser.SEMI, 0); }
		public Var_decContext var_dec() {
			return getRuleContext(Var_decContext.class,0);
		}
		public VarDecsContext(Var_decsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitVarDecs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_decsContext var_decs() throws RecognitionException {
		return var_decs(0);
	}

	private Var_decsContext var_decs(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Var_decsContext _localctx = new Var_decsContext(_ctx, _parentState);
		Var_decsContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_var_decs, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new VarDecContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(29);
			var_dec();
			}
			_ctx.stop = _input.LT(-1);
			setState(36);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new VarDecsContext(new Var_decsContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_var_decs);
					setState(31);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(32);
					match(SEMI);
					setState(33);
					var_dec();
					}
					} 
				}
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Var_decContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(WhileParser.ID, 0); }
		public Type_expContext type_exp() {
			return getRuleContext(Type_expContext.class,0);
		}
		public Var_decContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_dec; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitVar_dec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_decContext var_dec() throws RecognitionException {
		Var_decContext _localctx = new Var_decContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_var_dec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(ID);
			setState(40);
			match(T__0);
			setState(41);
			type_exp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_expContext extends ParserRuleContext {
		public Type_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_exp; }
	 
		public Type_expContext() { }
		public void copyFrom(Type_expContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrTypeContext extends Type_expContext {
		public TerminalNode INT() { return getToken(WhileParser.INT, 0); }
		public Type_expContext type_exp() {
			return getRuleContext(Type_expContext.class,0);
		}
		public ArrTypeContext(Type_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitArrType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolTypeContext extends Type_expContext {
		public BoolTypeContext(Type_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitBoolType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntTypeContext extends Type_expContext {
		public IntTypeContext(Type_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitIntType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_expContext type_exp() throws RecognitionException {
		Type_expContext _localctx = new Type_expContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type_exp);
		try {
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				_localctx = new IntTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				match(T__1);
				}
				break;
			case T__2:
				_localctx = new BoolTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				match(T__2);
				}
				break;
			case T__3:
				_localctx = new ArrTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(45);
				match(T__3);
				setState(46);
				match(T__4);
				setState(47);
				match(INT);
				setState(48);
				match(T__5);
				setState(49);
				match(T__6);
				setState(50);
				type_exp();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtsContext extends ParserRuleContext {
		public StmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmts; }
	 
		public StmtsContext() { }
		public void copyFrom(StmtsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Stmt_listContext extends StmtsContext {
		public StmtsContext stmts() {
			return getRuleContext(StmtsContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(WhileParser.SEMI, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public Stmt_listContext(StmtsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitStmt_list(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Stmt_singleContext extends StmtsContext {
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public Stmt_singleContext(StmtsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitStmt_single(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtsContext stmts() throws RecognitionException {
		return stmts(0);
	}

	private StmtsContext stmts(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StmtsContext _localctx = new StmtsContext(_ctx, _parentState);
		StmtsContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_stmts, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new Stmt_singleContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(54);
			stmt();
			}
			_ctx.stop = _input.LT(-1);
			setState(61);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Stmt_listContext(new StmtsContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_stmts);
					setState(56);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(57);
					match(SEMI);
					setState(58);
					stmt();
					}
					} 
				}
				setState(63);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PrintContext extends StmtContext {
		public AexpContext aexp() {
			return getRuleContext(AexpContext.class,0);
		}
		public PrintContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignContext extends StmtContext {
		public TerminalNode ID() { return getToken(WhileParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends StmtContext {
		public BexpContext bexp() {
			return getRuleContext(BexpContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public IfContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_stmt);
		try {
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new AssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				match(ID);
				setState(65);
				match(T__7);
				setState(66);
				expr();
				}
				break;
			case T__8:
				_localctx = new PrintContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				match(T__8);
				setState(68);
				aexp(0);
				}
				break;
			case T__9:
				_localctx = new IfContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(69);
				match(T__9);
				setState(70);
				bexp(0);
				setState(71);
				match(T__10);
				setState(72);
				stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public AexpContext aexp() {
			return getRuleContext(AexpContext.class,0);
		}
		public BexpContext bexp() {
			return getRuleContext(BexpContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expr);
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				aexp(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				bexp(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AexpContext extends ParserRuleContext {
		public AexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aexp; }
	 
		public AexpContext() { }
		public void copyFrom(AexpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarContext extends AexpContext {
		public TerminalNode ID() { return getToken(WhileParser.ID, 0); }
		public VarContext(AexpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParensContext extends AexpContext {
		public AexpContext aexp() {
			return getRuleContext(AexpContext.class,0);
		}
		public ParensContext(AexpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntContext extends AexpContext {
		public TerminalNode INT() { return getToken(WhileParser.INT, 0); }
		public IntContext(AexpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOpContext extends AexpContext {
		public List<AexpContext> aexp() {
			return getRuleContexts(AexpContext.class);
		}
		public AexpContext aexp(int i) {
			return getRuleContext(AexpContext.class,i);
		}
		public AopContext aop() {
			return getRuleContext(AopContext.class,0);
		}
		public BinaryOpContext(AexpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitBinaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AexpContext aexp() throws RecognitionException {
		return aexp(0);
	}

	private AexpContext aexp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AexpContext _localctx = new AexpContext(_ctx, _parentState);
		AexpContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_aexp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(81);
				match(INT);
				}
				break;
			case ID:
				{
				_localctx = new VarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				match(ID);
				}
				break;
			case T__11:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				match(T__11);
				setState(84);
				aexp(0);
				setState(85);
				match(T__12);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(95);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BinaryOpContext(new AexpContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_aexp);
					setState(89);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(90);
					aop();
					setState(91);
					aexp(5);
					}
					} 
				}
				setState(97);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BexpContext extends ParserRuleContext {
		public BexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bexp; }
	 
		public BexpContext() { }
		public void copyFrom(BexpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BEFalseContext extends BexpContext {
		public BEFalseContext(BexpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitBEFalse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BENotContext extends BexpContext {
		public BexpContext bexp() {
			return getRuleContext(BexpContext.class,0);
		}
		public BENotContext(BexpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitBENot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BERopContext extends BexpContext {
		public List<AexpContext> aexp() {
			return getRuleContexts(AexpContext.class);
		}
		public AexpContext aexp(int i) {
			return getRuleContext(AexpContext.class,i);
		}
		public RopContext rop() {
			return getRuleContext(RopContext.class,0);
		}
		public BERopContext(BexpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitBERop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BEBopContext extends BexpContext {
		public List<BexpContext> bexp() {
			return getRuleContexts(BexpContext.class);
		}
		public BexpContext bexp(int i) {
			return getRuleContext(BexpContext.class,i);
		}
		public BopContext bop() {
			return getRuleContext(BopContext.class,0);
		}
		public BEBopContext(BexpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitBEBop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BETrueContext extends BexpContext {
		public BETrueContext(BexpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitBETrue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BexpContext bexp() throws RecognitionException {
		return bexp(0);
	}

	private BexpContext bexp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BexpContext _localctx = new BexpContext(_ctx, _parentState);
		BexpContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_bexp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				{
				_localctx = new BETrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(99);
				match(T__13);
				}
				break;
			case T__14:
				{
				_localctx = new BEFalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(100);
				match(T__14);
				}
				break;
			case T__15:
				{
				_localctx = new BENotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(101);
				match(T__15);
				setState(102);
				bexp(3);
				}
				break;
			case T__11:
			case INT:
			case ID:
				{
				_localctx = new BERopContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(103);
				aexp(0);
				setState(104);
				rop();
				setState(105);
				aexp(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(115);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BEBopContext(new BexpContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_bexp);
					setState(109);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(110);
					bop();
					setState(111);
					bexp(2);
					}
					} 
				}
				setState(117);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RopContext extends ParserRuleContext {
		public RopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rop; }
	 
		public RopContext() { }
		public void copyFrom(RopContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RopGTContext extends RopContext {
		public TerminalNode GT() { return getToken(WhileParser.GT, 0); }
		public RopGTContext(RopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitRopGT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RopLTContext extends RopContext {
		public TerminalNode LT() { return getToken(WhileParser.LT, 0); }
		public RopLTContext(RopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitRopLT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RopContext rop() throws RecognitionException {
		RopContext _localctx = new RopContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_rop);
		try {
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GT:
				_localctx = new RopGTContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				match(GT);
				}
				break;
			case LT:
				_localctx = new RopLTContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				match(LT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BopContext extends ParserRuleContext {
		public BopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bop; }
	 
		public BopContext() { }
		public void copyFrom(BopContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BopORContext extends BopContext {
		public TerminalNode OR() { return getToken(WhileParser.OR, 0); }
		public BopORContext(BopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitBopOR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BopANDContext extends BopContext {
		public TerminalNode AND() { return getToken(WhileParser.AND, 0); }
		public BopANDContext(BopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitBopAND(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BopContext bop() throws RecognitionException {
		BopContext _localctx = new BopContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_bop);
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AND:
				_localctx = new BopANDContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				match(AND);
				}
				break;
			case OR:
				_localctx = new BopORContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				match(OR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AopContext extends ParserRuleContext {
		public AopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aop; }
	 
		public AopContext() { }
		public void copyFrom(AopContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DivContext extends AopContext {
		public TerminalNode DIV() { return getToken(WhileParser.DIV, 0); }
		public DivContext(AopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultContext extends AopContext {
		public TerminalNode MULT() { return getToken(WhileParser.MULT, 0); }
		public MultContext(AopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitMult(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusContext extends AopContext {
		public TerminalNode PLUS() { return getToken(WhileParser.PLUS, 0); }
		public PlusContext(AopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitPlus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinusContext extends AopContext {
		public TerminalNode MINUS() { return getToken(WhileParser.MINUS, 0); }
		public MinusContext(AopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WhileVisitor ) return ((WhileVisitor<? extends T>)visitor).visitMinus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AopContext aop() throws RecognitionException {
		AopContext _localctx = new AopContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_aop);
		try {
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				_localctx = new PlusContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(PLUS);
				}
				break;
			case MINUS:
				_localctx = new MinusContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				match(MINUS);
				}
				break;
			case MULT:
				_localctx = new MultContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(128);
				match(MULT);
				}
				break;
			case DIV:
				_localctx = new DivContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(129);
				match(DIV);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return var_decs_sempred((Var_decsContext)_localctx, predIndex);
		case 4:
			return stmts_sempred((StmtsContext)_localctx, predIndex);
		case 7:
			return aexp_sempred((AexpContext)_localctx, predIndex);
		case 8:
			return bexp_sempred((BexpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean var_decs_sempred(Var_decsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean stmts_sempred(StmtsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean aexp_sempred(AexpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean bexp_sempred(BexpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u0087\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3%\n\3"+
		"\f\3\16\3(\13\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\66"+
		"\n\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6>\n\6\f\6\16\6A\13\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\5\7M\n\7\3\b\3\b\5\bQ\n\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\5\tZ\n\t\3\t\3\t\3\t\3\t\7\t`\n\t\f\t\16\tc\13\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\nn\n\n\3\n\3\n\3\n\3\n\7\nt\n\n\f\n\16\nw\13"+
		"\n\3\13\3\13\5\13{\n\13\3\f\3\f\5\f\177\n\f\3\r\3\r\3\r\3\r\5\r\u0085"+
		"\n\r\3\r\2\6\4\n\20\22\16\2\4\6\b\n\f\16\20\22\24\26\30\2\2\2\u008d\2"+
		"\32\3\2\2\2\4\36\3\2\2\2\6)\3\2\2\2\b\65\3\2\2\2\n\67\3\2\2\2\fL\3\2\2"+
		"\2\16P\3\2\2\2\20Y\3\2\2\2\22m\3\2\2\2\24z\3\2\2\2\26~\3\2\2\2\30\u0084"+
		"\3\2\2\2\32\33\5\4\3\2\33\34\7\26\2\2\34\35\5\n\6\2\35\3\3\2\2\2\36\37"+
		"\b\3\1\2\37 \5\6\4\2 &\3\2\2\2!\"\f\4\2\2\"#\7\26\2\2#%\5\6\4\2$!\3\2"+
		"\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'\5\3\2\2\2(&\3\2\2\2)*\7\25\2\2*"+
		"+\7\3\2\2+,\5\b\5\2,\7\3\2\2\2-\66\7\4\2\2.\66\7\5\2\2/\60\7\6\2\2\60"+
		"\61\7\7\2\2\61\62\7\24\2\2\62\63\7\b\2\2\63\64\7\t\2\2\64\66\5\b\5\2\65"+
		"-\3\2\2\2\65.\3\2\2\2\65/\3\2\2\2\66\t\3\2\2\2\678\b\6\1\289\5\f\7\29"+
		"?\3\2\2\2:;\f\4\2\2;<\7\26\2\2<>\5\f\7\2=:\3\2\2\2>A\3\2\2\2?=\3\2\2\2"+
		"?@\3\2\2\2@\13\3\2\2\2A?\3\2\2\2BC\7\25\2\2CD\7\n\2\2DM\5\16\b\2EF\7\13"+
		"\2\2FM\5\20\t\2GH\7\f\2\2HI\5\22\n\2IJ\7\r\2\2JK\5\f\7\2KM\3\2\2\2LB\3"+
		"\2\2\2LE\3\2\2\2LG\3\2\2\2M\r\3\2\2\2NQ\5\20\t\2OQ\5\22\n\2PN\3\2\2\2"+
		"PO\3\2\2\2Q\17\3\2\2\2RS\b\t\1\2SZ\7\24\2\2TZ\7\25\2\2UV\7\16\2\2VW\5"+
		"\20\t\2WX\7\17\2\2XZ\3\2\2\2YR\3\2\2\2YT\3\2\2\2YU\3\2\2\2Za\3\2\2\2["+
		"\\\f\6\2\2\\]\5\30\r\2]^\5\20\t\7^`\3\2\2\2_[\3\2\2\2`c\3\2\2\2a_\3\2"+
		"\2\2ab\3\2\2\2b\21\3\2\2\2ca\3\2\2\2de\b\n\1\2en\7\20\2\2fn\7\21\2\2g"+
		"h\7\22\2\2hn\5\22\n\5ij\5\20\t\2jk\5\24\13\2kl\5\20\t\2ln\3\2\2\2md\3"+
		"\2\2\2mf\3\2\2\2mg\3\2\2\2mi\3\2\2\2nu\3\2\2\2op\f\3\2\2pq\5\26\f\2qr"+
		"\5\22\n\4rt\3\2\2\2so\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2v\23\3\2\2"+
		"\2wu\3\2\2\2x{\7\33\2\2y{\7\34\2\2zx\3\2\2\2zy\3\2\2\2{\25\3\2\2\2|\177"+
		"\7\35\2\2}\177\7\36\2\2~|\3\2\2\2~}\3\2\2\2\177\27\3\2\2\2\u0080\u0085"+
		"\7\30\2\2\u0081\u0085\7\32\2\2\u0082\u0085\7\27\2\2\u0083\u0085\7\31\2"+
		"\2\u0084\u0080\3\2\2\2\u0084\u0081\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0083"+
		"\3\2\2\2\u0085\31\3\2\2\2\16&\65?LPYamuz~\u0084";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}