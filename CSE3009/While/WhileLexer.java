// Generated from While.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WhileLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, NEWLINE=17, 
		INT=18, ID=19, SEMI=20, MULT=21, PLUS=22, DIV=23, MINUS=24, GT=25, LT=26, 
		AND=27, OR=28;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "NEWLINE", 
		"INT", "ID", "SEMI", "MULT", "PLUS", "DIV", "MINUS", "GT", "LT", "AND", 
		"OR"
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


	public WhileLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "While.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\36\u0099\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\22\6\22v\n\22\r\22\16\22w\3\22\3\22\3\23\6\23}\n\23\r"+
		"\23\16\23~\3\24\3\24\7\24\u0083\n\24\f\24\16\24\u0086\13\24\3\25\3\25"+
		"\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34"+
		"\3\35\3\35\2\2\36\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36\3\2\6\5\2\f\f\17\17\"\"\3\2\62;\3\2c|\6\2\62;C\\aac|\2\u009b"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3"+
		"\2\2\2\5=\3\2\2\2\7A\3\2\2\2\tF\3\2\2\2\13L\3\2\2\2\rN\3\2\2\2\17P\3\2"+
		"\2\2\21S\3\2\2\2\23U\3\2\2\2\25[\3\2\2\2\27^\3\2\2\2\31c\3\2\2\2\33e\3"+
		"\2\2\2\35g\3\2\2\2\37l\3\2\2\2!r\3\2\2\2#u\3\2\2\2%|\3\2\2\2\'\u0080\3"+
		"\2\2\2)\u0087\3\2\2\2+\u0089\3\2\2\2-\u008b\3\2\2\2/\u008d\3\2\2\2\61"+
		"\u008f\3\2\2\2\63\u0091\3\2\2\2\65\u0093\3\2\2\2\67\u0095\3\2\2\29\u0097"+
		"\3\2\2\2;<\7<\2\2<\4\3\2\2\2=>\7k\2\2>?\7p\2\2?@\7v\2\2@\6\3\2\2\2AB\7"+
		"d\2\2BC\7q\2\2CD\7q\2\2DE\7n\2\2E\b\3\2\2\2FG\7c\2\2GH\7t\2\2HI\7t\2\2"+
		"IJ\7c\2\2JK\7{\2\2K\n\3\2\2\2LM\7]\2\2M\f\3\2\2\2NO\7_\2\2O\16\3\2\2\2"+
		"PQ\7q\2\2QR\7h\2\2R\20\3\2\2\2ST\7?\2\2T\22\3\2\2\2UV\7r\2\2VW\7t\2\2"+
		"WX\7k\2\2XY\7p\2\2YZ\7v\2\2Z\24\3\2\2\2[\\\7k\2\2\\]\7h\2\2]\26\3\2\2"+
		"\2^_\7v\2\2_`\7j\2\2`a\7g\2\2ab\7p\2\2b\30\3\2\2\2cd\7*\2\2d\32\3\2\2"+
		"\2ef\7+\2\2f\34\3\2\2\2gh\7v\2\2hi\7t\2\2ij\7w\2\2jk\7g\2\2k\36\3\2\2"+
		"\2lm\7h\2\2mn\7c\2\2no\7n\2\2op\7u\2\2pq\7g\2\2q \3\2\2\2rs\7#\2\2s\""+
		"\3\2\2\2tv\t\2\2\2ut\3\2\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2xy\3\2\2\2y"+
		"z\b\22\2\2z$\3\2\2\2{}\t\3\2\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2"+
		"\2\2\177&\3\2\2\2\u0080\u0084\t\4\2\2\u0081\u0083\t\5\2\2\u0082\u0081"+
		"\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"(\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0088\7=\2\2\u0088*\3\2\2\2\u0089"+
		"\u008a\7,\2\2\u008a,\3\2\2\2\u008b\u008c\7-\2\2\u008c.\3\2\2\2\u008d\u008e"+
		"\7\61\2\2\u008e\60\3\2\2\2\u008f\u0090\7/\2\2\u0090\62\3\2\2\2\u0091\u0092"+
		"\7@\2\2\u0092\64\3\2\2\2\u0093\u0094\7>\2\2\u0094\66\3\2\2\2\u0095\u0096"+
		"\7(\2\2\u00968\3\2\2\2\u0097\u0098\7~\2\2\u0098:\3\2\2\2\6\2w~\u0084\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}