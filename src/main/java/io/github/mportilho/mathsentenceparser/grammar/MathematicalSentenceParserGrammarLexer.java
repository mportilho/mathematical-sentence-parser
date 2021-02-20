// Generated from C:/Users/marce/IdeaProjects/ExpressionReader/src/io/github/mportilho/mathsentenceparser\MathematicalSentenceParserGrammar.g4 by ANTLR 4.9.1

    package io.github.mportilho.mathsentenceparser.grammar;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MathematicalSentenceParserGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, THEN=2, ELSE=3, ELSEIF=4, ENDIF=5, AND=6, OR=7, XOR=8, XNOR=9, NAND=10, 
		NOR=11, TRUE=12, FALSE=13, MULT=14, DIV=15, PLUS=16, MINUS=17, PERCENT=18, 
		MODULO=19, MODULUS=20, EXCLAMATION=21, EXPONENTIATION=22, ROOT=23, SQRT=24, 
		BINARY_LOGARITHM=25, NATURAL_LOGARITHM=26, COMMOM_LOGARITHM=27, LOGARITHM=28, 
		SUMMATION=29, PRODUCT_SEQUENCE=30, SUMMATION_VARIABLE=31, PRODUCT_SEQUENCE_VARIABLE=32, 
		MINUS_PARENTHESIS=33, SINE=34, COSINE=35, TANGENT=36, ARCSINE=37, ARCCOSINE=38, 
		ARCTANGENT=39, ARCTANGENT2=40, HYPERBOLIC_SINE=41, HYPERBOLIC_COSINE=42, 
		HYPERBOLIC_TANGENT=43, INVERSE_HYPERBOLIC_SINE=44, INVERSE_HYPERBOLIC_COSINE=45, 
		INVERSE_HYPERBOLIC_TANGENT=46, PI=47, EULER=48, DEGREE=49, GT=50, GE=51, 
		LT=52, LE=53, EQ=54, NEQ=55, NOT=56, R_UP=57, R_DOWN=58, R_CEILING=59, 
		R_FLOOR=60, R_HALF_UP=61, R_HALF_DOWN=62, R_HALF_EVEN=63, R_UNNECESSARY=64, 
		DATE_OPERATIONS=65, TIME_OPERATIONS=66, NOW_DATE=67, NOW_TIME=68, NOW_DATETIME=69, 
		PLUS_DAYS=70, PLUS_MONTHS=71, PLUS_YEARS=72, PLUS_HOURS=73, PLUS_MINUTES=74, 
		PLUS_SECONDS=75, MINUS_DAYS=76, MINUS_MONTHS=77, MINUS_YEARS=78, MINUS_HOURS=79, 
		MINUS_MINUTES=80, MINUS_SECONDS=81, WITH_DAYS=82, WITH_MONTHS=83, WITH_YEARS=84, 
		WITH_HOURS=85, WITH_MINUTES=86, WITH_SECONDS=87, LPAREN=88, RPAREN=89, 
		LBLACKET=90, RBLACKET=91, QUOTES=92, ASSIGNMENT=93, COMMA=94, SEMI=95, 
		PERIOD=96, CONTAINS=97, JSON_PATH=98, FUNCTION_PREFIX=99, IDENTIFIER=100, 
		NEGATIVE_IDENTIFIER=101, STRING=102, NUMBER=103, POSITIVE=104, DATE=105, 
		TIME=106, DATETIME=107, BOOLEAN_TYPE=108, NUMBER_TYPE=109, STRING_TYPE=110, 
		DATE_TYPE=111, TIME_TYPE=112, DATETIME_TYPE=113, LIST_TYPE=114, ASSIGNABLE_TYPES=115, 
		DAY_PART=116, MONTH_PART=117, HOUR_PART=118, MINUTE_SECOND_PART=119, COMMENT=120, 
		WS=121;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "THEN", "ELSE", "ELSEIF", "ENDIF", "AND", "OR", "XOR", "XNOR", 
			"NAND", "NOR", "TRUE", "FALSE", "MULT", "DIV", "PLUS", "MINUS", "PERCENT", 
			"MODULO", "MODULUS", "EXCLAMATION", "EXPONENTIATION", "ROOT", "SQRT", 
			"BINARY_LOGARITHM", "NATURAL_LOGARITHM", "COMMOM_LOGARITHM", "LOGARITHM", 
			"SUMMATION", "PRODUCT_SEQUENCE", "SUMMATION_VARIABLE", "PRODUCT_SEQUENCE_VARIABLE", 
			"MINUS_PARENTHESIS", "SINE", "COSINE", "TANGENT", "ARCSINE", "ARCCOSINE", 
			"ARCTANGENT", "ARCTANGENT2", "HYPERBOLIC_SINE", "HYPERBOLIC_COSINE", 
			"HYPERBOLIC_TANGENT", "INVERSE_HYPERBOLIC_SINE", "INVERSE_HYPERBOLIC_COSINE", 
			"INVERSE_HYPERBOLIC_TANGENT", "PI", "EULER", "DEGREE", "GT", "GE", "LT", 
			"LE", "EQ", "NEQ", "NOT", "R_UP", "R_DOWN", "R_CEILING", "R_FLOOR", "R_HALF_UP", 
			"R_HALF_DOWN", "R_HALF_EVEN", "R_UNNECESSARY", "DATE_OPERATIONS", "TIME_OPERATIONS", 
			"NOW_DATE", "NOW_TIME", "NOW_DATETIME", "PLUS_DAYS", "PLUS_MONTHS", "PLUS_YEARS", 
			"PLUS_HOURS", "PLUS_MINUTES", "PLUS_SECONDS", "MINUS_DAYS", "MINUS_MONTHS", 
			"MINUS_YEARS", "MINUS_HOURS", "MINUS_MINUTES", "MINUS_SECONDS", "WITH_DAYS", 
			"WITH_MONTHS", "WITH_YEARS", "WITH_HOURS", "WITH_MINUTES", "WITH_SECONDS", 
			"LPAREN", "RPAREN", "LBLACKET", "RBLACKET", "QUOTES", "ASSIGNMENT", "COMMA", 
			"SEMI", "PERIOD", "CONTAINS", "JSON_PATH", "FUNCTION_PREFIX", "IDENTIFIER", 
			"NEGATIVE_IDENTIFIER", "STRING", "NUMBER", "POSITIVE", "DATE", "TIME", 
			"DATETIME", "BOOLEAN_TYPE", "NUMBER_TYPE", "STRING_TYPE", "DATE_TYPE", 
			"TIME_TYPE", "DATETIME_TYPE", "LIST_TYPE", "ASSIGNABLE_TYPES", "DAY_PART", 
			"MONTH_PART", "HOUR_PART", "MINUTE_SECOND_PART", "IdentifierText", "NegativeSymbol", 
			"Decimal", "PositiveNumber", "OctalDigits", "HexDigits", "IdentifierStart", 
			"IdentifierPart", "NumberPart", "Colon", "Slash", "JsonPathSeparator", 
			"RawTextNoWhiteSpace", "RawAlphabeticNoWhiteSpace", "MinusParenthesisOperator", 
			"COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'then'", "'else'", "'elsif'", "'endif'", "'and'", "'or'", 
			"'xor'", "'xnor'", "'nand'", "'nor'", "'true'", "'false'", "'*'", "'/'", 
			"'+'", "'-'", "'%'", "'mod'", "'|'", "'!'", "'^'", null, "'sqrt'", "'lb'", 
			"'ln'", "'log10'", "'log'", "'S['", "'P['", "'S'", "'P'", null, "'sin'", 
			"'cos'", "'tan'", "'asin'", "'acos'", "'atan'", "'atan2'", "'sinh'", 
			"'cosh'", "'tanh'", "'asinh'", "'acosh'", "'atanh'", null, null, null, 
			"'>'", "'>='", "'<'", "'<='", "'='", null, null, "'up'", "'down'", "'ceiling'", 
			"'floor'", "'halfUp'", "'halfDown'", "'halfEven'", "'unnecessary'", null, 
			null, "'currDate'", "'currTime'", "'currDateTime'", "'plusDays'", "'plusMonths'", 
			"'plusYears'", "'plusHours'", "'plusMinutes'", "'plusSeconds'", "'minusDays'", 
			"'minusMonths'", "'minusYears'", "'minusHours'", "'minusMinutes'", "'minusSeconds'", 
			"'withDays'", "'withMonths'", "'withYears'", "'withHours'", "'withMinutes'", 
			"'withSeconds'", "'('", "')'", "'['", "']'", "'''", "':='", "','", "';'", 
			"'.'", "'contains'", null, "'f.'", null, null, null, null, null, null, 
			null, null, "'<bool>'", "'<number>'", "'<text>'", "'<date>'", "'<time>'", 
			"'<datetime>'", "'<list>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "THEN", "ELSE", "ELSEIF", "ENDIF", "AND", "OR", "XOR", "XNOR", 
			"NAND", "NOR", "TRUE", "FALSE", "MULT", "DIV", "PLUS", "MINUS", "PERCENT", 
			"MODULO", "MODULUS", "EXCLAMATION", "EXPONENTIATION", "ROOT", "SQRT", 
			"BINARY_LOGARITHM", "NATURAL_LOGARITHM", "COMMOM_LOGARITHM", "LOGARITHM", 
			"SUMMATION", "PRODUCT_SEQUENCE", "SUMMATION_VARIABLE", "PRODUCT_SEQUENCE_VARIABLE", 
			"MINUS_PARENTHESIS", "SINE", "COSINE", "TANGENT", "ARCSINE", "ARCCOSINE", 
			"ARCTANGENT", "ARCTANGENT2", "HYPERBOLIC_SINE", "HYPERBOLIC_COSINE", 
			"HYPERBOLIC_TANGENT", "INVERSE_HYPERBOLIC_SINE", "INVERSE_HYPERBOLIC_COSINE", 
			"INVERSE_HYPERBOLIC_TANGENT", "PI", "EULER", "DEGREE", "GT", "GE", "LT", 
			"LE", "EQ", "NEQ", "NOT", "R_UP", "R_DOWN", "R_CEILING", "R_FLOOR", "R_HALF_UP", 
			"R_HALF_DOWN", "R_HALF_EVEN", "R_UNNECESSARY", "DATE_OPERATIONS", "TIME_OPERATIONS", 
			"NOW_DATE", "NOW_TIME", "NOW_DATETIME", "PLUS_DAYS", "PLUS_MONTHS", "PLUS_YEARS", 
			"PLUS_HOURS", "PLUS_MINUTES", "PLUS_SECONDS", "MINUS_DAYS", "MINUS_MONTHS", 
			"MINUS_YEARS", "MINUS_HOURS", "MINUS_MINUTES", "MINUS_SECONDS", "WITH_DAYS", 
			"WITH_MONTHS", "WITH_YEARS", "WITH_HOURS", "WITH_MINUTES", "WITH_SECONDS", 
			"LPAREN", "RPAREN", "LBLACKET", "RBLACKET", "QUOTES", "ASSIGNMENT", "COMMA", 
			"SEMI", "PERIOD", "CONTAINS", "JSON_PATH", "FUNCTION_PREFIX", "IDENTIFIER", 
			"NEGATIVE_IDENTIFIER", "STRING", "NUMBER", "POSITIVE", "DATE", "TIME", 
			"DATETIME", "BOOLEAN_TYPE", "NUMBER_TYPE", "STRING_TYPE", "DATE_TYPE", 
			"TIME_TYPE", "DATETIME_TYPE", "LIST_TYPE", "ASSIGNABLE_TYPES", "DAY_PART", 
			"MONTH_PART", "HOUR_PART", "MINUTE_SECOND_PART", "COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public MathematicalSentenceParserGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MathematicalSentenceParserGrammar.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2{\u044c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t\u0080"+
		"\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084\4\u0085"+
		"\t\u0085\4\u0086\t\u0086\4\u0087\t\u0087\4\u0088\t\u0088\4\u0089\t\u0089"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\30\5\30\u016a\n\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3#"+
		"\3#\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3"+
		"(\3(\3(\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3"+
		",\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3\60\5\60\u01d0"+
		"\n\60\3\60\3\60\3\60\3\60\5\60\u01d6\n\60\3\61\5\61\u01d9\n\61\3\61\3"+
		"\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3"+
		"\62\5\62\u01eb\n\62\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\66\3\66\3\66"+
		"\3\67\3\67\38\38\38\38\58\u01fd\n8\39\39\39\59\u0202\n9\3:\3:\3:\3;\3"+
		";\3;\3;\3;\3<\3<\3<\3<\3<\3<\3<\3<\3=\3=\3=\3=\3=\3=\3>\3>\3>\3>\3>\3"+
		">\3>\3?\3?\3?\3?\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3@\3@\3@\3@\3A\3A\3A\3"+
		"A\3A\3A\3A\3A\3A\3A\3A\3A\3B\3B\3B\3B\3B\3B\3B\3B\3B\5B\u0248\nB\3C\3"+
		"C\3C\3C\3C\3C\3C\3C\3C\5C\u0253\nC\3D\3D\3D\3D\3D\3D\3D\3D\3D\3E\3E\3"+
		"E\3E\3E\3E\3E\3E\3E\3F\3F\3F\3F\3F\3F\3F\3F\3F\3F\3F\3F\3F\3G\3G\3G\3"+
		"G\3G\3G\3G\3G\3G\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3I\3I\3I\3I\3I\3I\3"+
		"I\3I\3I\3I\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3K\3K\3K\3K\3K\3K\3K\3K\3K\3"+
		"K\3K\3K\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3M\3M\3M\3"+
		"M\3M\3N\3N\3N\3N\3N\3N\3N\3N\3N\3N\3N\3N\3O\3O\3O\3O\3O\3O\3O\3O\3O\3"+
		"O\3O\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3"+
		"Q\3Q\3Q\3R\3R\3R\3R\3R\3R\3R\3R\3R\3R\3R\3R\3R\3S\3S\3S\3S\3S\3S\3S\3"+
		"S\3S\3T\3T\3T\3T\3T\3T\3T\3T\3T\3T\3T\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3"+
		"V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3X\3"+
		"X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3Y\3Y\3Z\3Z\3[\3[\3\\\3\\\3]\3]\3^\3^"+
		"\3^\3_\3_\3`\3`\3a\3a\3b\3b\3b\3b\3b\3b\3b\3b\3b\3c\3c\3c\3c\3c\3d\3d"+
		"\3d\3e\3e\3f\3f\3f\3g\3g\7g\u0365\ng\fg\16g\u0368\13g\3g\3g\3h\3h\3h\5"+
		"h\u036f\nh\3i\3i\3j\3j\3j\3j\3j\3j\3j\3j\3j\3k\3k\3k\3k\3k\3k\5k\u0382"+
		"\nk\3l\3l\3l\3l\3m\3m\3m\3m\3m\3m\3m\3n\3n\3n\3n\3n\3n\3n\3n\3n\3o\3o"+
		"\3o\3o\3o\3o\3o\3p\3p\3p\3p\3p\3p\3p\3q\3q\3q\3q\3q\3q\3q\3r\3r\3r\3r"+
		"\3r\3r\3r\3r\3r\3r\3r\3s\3s\3s\3s\3s\3s\3s\3t\3t\3t\3t\5t\u03c3\nt\3u"+
		"\3u\3u\3u\3u\3u\5u\u03cb\nu\3v\3v\3v\3v\5v\u03d1\nv\3w\3w\3w\3w\5w\u03d7"+
		"\nw\3x\3x\3x\3y\3y\7y\u03de\ny\fy\16y\u03e1\13y\3z\3z\3{\5{\u03e6\n{\3"+
		"{\6{\u03e9\n{\r{\16{\u03ea\3{\3{\6{\u03ef\n{\r{\16{\u03f0\5{\u03f3\n{"+
		"\3|\6|\u03f6\n|\r|\16|\u03f7\3}\3}\6}\u03fc\n}\r}\16}\u03fd\3~\3~\3~\3"+
		"~\6~\u0404\n~\r~\16~\u0405\3\177\3\177\3\u0080\3\u0080\3\u0080\5\u0080"+
		"\u040d\n\u0080\3\u0081\3\u0081\3\u0082\3\u0082\3\u0083\3\u0083\3\u0084"+
		"\3\u0084\3\u0084\3\u0085\3\u0085\3\u0085\6\u0085\u041b\n\u0085\r\u0085"+
		"\16\u0085\u041c\3\u0086\3\u0086\3\u0086\6\u0086\u0422\n\u0086\r\u0086"+
		"\16\u0086\u0423\3\u0087\3\u0087\3\u0087\3\u0088\3\u0088\3\u0088\3\u0088"+
		"\7\u0088\u042d\n\u0088\f\u0088\16\u0088\u0430\13\u0088\3\u0088\5\u0088"+
		"\u0433\n\u0088\3\u0088\3\u0088\3\u0088\3\u0088\3\u0088\7\u0088\u043a\n"+
		"\u0088\f\u0088\16\u0088\u043d\13\u0088\3\u0088\3\u0088\3\u0088\5\u0088"+
		"\u0442\n\u0088\3\u0088\3\u0088\3\u0089\6\u0089\u0447\n\u0089\r\u0089\16"+
		"\u0089\u0448\3\u0089\3\u0089\3\u043b\2\u008a\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S"+
		"+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081"+
		"B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093K\u0095"+
		"L\u0097M\u0099N\u009bO\u009dP\u009fQ\u00a1R\u00a3S\u00a5T\u00a7U\u00a9"+
		"V\u00abW\u00adX\u00afY\u00b1Z\u00b3[\u00b5\\\u00b7]\u00b9^\u00bb_\u00bd"+
		"`\u00bfa\u00c1b\u00c3c\u00c5d\u00c7e\u00c9f\u00cbg\u00cdh\u00cfi\u00d1"+
		"j\u00d3k\u00d5l\u00d7m\u00d9n\u00dbo\u00ddp\u00dfq\u00e1r\u00e3s\u00e5"+
		"t\u00e7u\u00e9v\u00ebw\u00edx\u00efy\u00f1\2\u00f3\2\u00f5\2\u00f7\2\u00f9"+
		"\2\u00fb\2\u00fd\2\u00ff\2\u0101\2\u0103\2\u0105\2\u0107\2\u0109\2\u010b"+
		"\2\u010d\2\u010fz\u0111{\3\2\r\3\2))\5\2C\\aac|\6\2\62;C\\aac|\3\2\62"+
		";\5\2\62;CHch\4\2C\\c|\4\2$$^^\7\2\13\f\16\17\"\"$$^^\b\2\13\f\16\17\""+
		"\"$$\62;^^\4\2\f\f\17\17\5\2\13\f\16\17\"\"\2\u0473\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2"+
		"?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3"+
		"\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2"+
		"\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2"+
		"e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3"+
		"\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2"+
		"\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087"+
		"\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2"+
		"\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099"+
		"\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2"+
		"\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7\3\2\2\2\2\u00a9\3\2\2\2\2\u00ab"+
		"\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1\3\2\2\2\2\u00b3\3\2\2"+
		"\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2\2\2\u00b9\3\2\2\2\2\u00bb\3\2\2\2\2\u00bd"+
		"\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1\3\2\2\2\2\u00c3\3\2\2\2\2\u00c5\3\2\2"+
		"\2\2\u00c7\3\2\2\2\2\u00c9\3\2\2\2\2\u00cb\3\2\2\2\2\u00cd\3\2\2\2\2\u00cf"+
		"\3\2\2\2\2\u00d1\3\2\2\2\2\u00d3\3\2\2\2\2\u00d5\3\2\2\2\2\u00d7\3\2\2"+
		"\2\2\u00d9\3\2\2\2\2\u00db\3\2\2\2\2\u00dd\3\2\2\2\2\u00df\3\2\2\2\2\u00e1"+
		"\3\2\2\2\2\u00e3\3\2\2\2\2\u00e5\3\2\2\2\2\u00e7\3\2\2\2\2\u00e9\3\2\2"+
		"\2\2\u00eb\3\2\2\2\2\u00ed\3\2\2\2\2\u00ef\3\2\2\2\2\u010f\3\2\2\2\2\u0111"+
		"\3\2\2\2\3\u0113\3\2\2\2\5\u0116\3\2\2\2\7\u011b\3\2\2\2\t\u0120\3\2\2"+
		"\2\13\u0126\3\2\2\2\r\u012c\3\2\2\2\17\u0130\3\2\2\2\21\u0133\3\2\2\2"+
		"\23\u0137\3\2\2\2\25\u013c\3\2\2\2\27\u0141\3\2\2\2\31\u0145\3\2\2\2\33"+
		"\u014a\3\2\2\2\35\u0150\3\2\2\2\37\u0152\3\2\2\2!\u0154\3\2\2\2#\u0156"+
		"\3\2\2\2%\u0158\3\2\2\2\'\u015a\3\2\2\2)\u015e\3\2\2\2+\u0160\3\2\2\2"+
		"-\u0162\3\2\2\2/\u0169\3\2\2\2\61\u016b\3\2\2\2\63\u0170\3\2\2\2\65\u0173"+
		"\3\2\2\2\67\u0176\3\2\2\29\u017c\3\2\2\2;\u0180\3\2\2\2=\u0183\3\2\2\2"+
		"?\u0186\3\2\2\2A\u0188\3\2\2\2C\u018a\3\2\2\2E\u018c\3\2\2\2G\u0190\3"+
		"\2\2\2I\u0194\3\2\2\2K\u0198\3\2\2\2M\u019d\3\2\2\2O\u01a2\3\2\2\2Q\u01a7"+
		"\3\2\2\2S\u01ad\3\2\2\2U\u01b2\3\2\2\2W\u01b7\3\2\2\2Y\u01bc\3\2\2\2["+
		"\u01c2\3\2\2\2]\u01c8\3\2\2\2_\u01cf\3\2\2\2a\u01d8\3\2\2\2c\u01ea\3\2"+
		"\2\2e\u01ec\3\2\2\2g\u01ee\3\2\2\2i\u01f1\3\2\2\2k\u01f3\3\2\2\2m\u01f6"+
		"\3\2\2\2o\u01fc\3\2\2\2q\u0201\3\2\2\2s\u0203\3\2\2\2u\u0206\3\2\2\2w"+
		"\u020b\3\2\2\2y\u0213\3\2\2\2{\u0219\3\2\2\2}\u0220\3\2\2\2\177\u0229"+
		"\3\2\2\2\u0081\u0232\3\2\2\2\u0083\u0247\3\2\2\2\u0085\u0252\3\2\2\2\u0087"+
		"\u0254\3\2\2\2\u0089\u025d\3\2\2\2\u008b\u0266\3\2\2\2\u008d\u0273\3\2"+
		"\2\2\u008f\u027c\3\2\2\2\u0091\u0287\3\2\2\2\u0093\u0291\3\2\2\2\u0095"+
		"\u029b\3\2\2\2\u0097\u02a7\3\2\2\2\u0099\u02b3\3\2\2\2\u009b\u02bd\3\2"+
		"\2\2\u009d\u02c9\3\2\2\2\u009f\u02d4\3\2\2\2\u00a1\u02df\3\2\2\2\u00a3"+
		"\u02ec\3\2\2\2\u00a5\u02f9\3\2\2\2\u00a7\u0302\3\2\2\2\u00a9\u030d\3\2"+
		"\2\2\u00ab\u0317\3\2\2\2\u00ad\u0321\3\2\2\2\u00af\u032d\3\2\2\2\u00b1"+
		"\u0339\3\2\2\2\u00b3\u033b\3\2\2\2\u00b5\u033d\3\2\2\2\u00b7\u033f\3\2"+
		"\2\2\u00b9\u0341\3\2\2\2\u00bb\u0343\3\2\2\2\u00bd\u0346\3\2\2\2\u00bf"+
		"\u0348\3\2\2\2\u00c1\u034a\3\2\2\2\u00c3\u034c\3\2\2\2\u00c5\u0355\3\2"+
		"\2\2\u00c7\u035a\3\2\2\2\u00c9\u035d\3\2\2\2\u00cb\u035f\3\2\2\2\u00cd"+
		"\u0362\3\2\2\2\u00cf\u036e\3\2\2\2\u00d1\u0370\3\2\2\2\u00d3\u0372\3\2"+
		"\2\2\u00d5\u037b\3\2\2\2\u00d7\u0383\3\2\2\2\u00d9\u0387\3\2\2\2\u00db"+
		"\u038e\3\2\2\2\u00dd\u0397\3\2\2\2\u00df\u039e\3\2\2\2\u00e1\u03a5\3\2"+
		"\2\2\u00e3\u03ac\3\2\2\2\u00e5\u03b7\3\2\2\2\u00e7\u03c2\3\2\2\2\u00e9"+
		"\u03ca\3\2\2\2\u00eb\u03d0\3\2\2\2\u00ed\u03d6\3\2\2\2\u00ef\u03d8\3\2"+
		"\2\2\u00f1\u03db\3\2\2\2\u00f3\u03e2\3\2\2\2\u00f5\u03e5\3\2\2\2\u00f7"+
		"\u03f5\3\2\2\2\u00f9\u03f9\3\2\2\2\u00fb\u03ff\3\2\2\2\u00fd\u0407\3\2"+
		"\2\2\u00ff\u040c\3\2\2\2\u0101\u040e\3\2\2\2\u0103\u0410\3\2\2\2\u0105"+
		"\u0412\3\2\2\2\u0107\u0414\3\2\2\2\u0109\u041a\3\2\2\2\u010b\u0421\3\2"+
		"\2\2\u010d\u0425\3\2\2\2\u010f\u0441\3\2\2\2\u0111\u0446\3\2\2\2\u0113"+
		"\u0114\7k\2\2\u0114\u0115\7h\2\2\u0115\4\3\2\2\2\u0116\u0117\7v\2\2\u0117"+
		"\u0118\7j\2\2\u0118\u0119\7g\2\2\u0119\u011a\7p\2\2\u011a\6\3\2\2\2\u011b"+
		"\u011c\7g\2\2\u011c\u011d\7n\2\2\u011d\u011e\7u\2\2\u011e\u011f\7g\2\2"+
		"\u011f\b\3\2\2\2\u0120\u0121\7g\2\2\u0121\u0122\7n\2\2\u0122\u0123\7u"+
		"\2\2\u0123\u0124\7k\2\2\u0124\u0125\7h\2\2\u0125\n\3\2\2\2\u0126\u0127"+
		"\7g\2\2\u0127\u0128\7p\2\2\u0128\u0129\7f\2\2\u0129\u012a\7k\2\2\u012a"+
		"\u012b\7h\2\2\u012b\f\3\2\2\2\u012c\u012d\7c\2\2\u012d\u012e\7p\2\2\u012e"+
		"\u012f\7f\2\2\u012f\16\3\2\2\2\u0130\u0131\7q\2\2\u0131\u0132\7t\2\2\u0132"+
		"\20\3\2\2\2\u0133\u0134\7z\2\2\u0134\u0135\7q\2\2\u0135\u0136\7t\2\2\u0136"+
		"\22\3\2\2\2\u0137\u0138\7z\2\2\u0138\u0139\7p\2\2\u0139\u013a\7q\2\2\u013a"+
		"\u013b\7t\2\2\u013b\24\3\2\2\2\u013c\u013d\7p\2\2\u013d\u013e\7c\2\2\u013e"+
		"\u013f\7p\2\2\u013f\u0140\7f\2\2\u0140\26\3\2\2\2\u0141\u0142\7p\2\2\u0142"+
		"\u0143\7q\2\2\u0143\u0144\7t\2\2\u0144\30\3\2\2\2\u0145\u0146\7v\2\2\u0146"+
		"\u0147\7t\2\2\u0147\u0148\7w\2\2\u0148\u0149\7g\2\2\u0149\32\3\2\2\2\u014a"+
		"\u014b\7h\2\2\u014b\u014c\7c\2\2\u014c\u014d\7n\2\2\u014d\u014e\7u\2\2"+
		"\u014e\u014f\7g\2\2\u014f\34\3\2\2\2\u0150\u0151\7,\2\2\u0151\36\3\2\2"+
		"\2\u0152\u0153\7\61\2\2\u0153 \3\2\2\2\u0154\u0155\7-\2\2\u0155\"\3\2"+
		"\2\2\u0156\u0157\7/\2\2\u0157$\3\2\2\2\u0158\u0159\7\'\2\2\u0159&\3\2"+
		"\2\2\u015a\u015b\7o\2\2\u015b\u015c\7q\2\2\u015c\u015d\7f\2\2\u015d(\3"+
		"\2\2\2\u015e\u015f\7~\2\2\u015f*\3\2\2\2\u0160\u0161\7#\2\2\u0161,\3\2"+
		"\2\2\u0162\u0163\7`\2\2\u0163.\3\2\2\2\u0164\u0165\7t\2\2\u0165\u0166"+
		"\7q\2\2\u0166\u0167\7q\2\2\u0167\u016a\7v\2\2\u0168\u016a\7\u221c\2\2"+
		"\u0169\u0164\3\2\2\2\u0169\u0168\3\2\2\2\u016a\60\3\2\2\2\u016b\u016c"+
		"\7u\2\2\u016c\u016d\7s\2\2\u016d\u016e\7t\2\2\u016e\u016f\7v\2\2\u016f"+
		"\62\3\2\2\2\u0170\u0171\7n\2\2\u0171\u0172\7d\2\2\u0172\64\3\2\2\2\u0173"+
		"\u0174\7n\2\2\u0174\u0175\7p\2\2\u0175\66\3\2\2\2\u0176\u0177\7n\2\2\u0177"+
		"\u0178\7q\2\2\u0178\u0179\7i\2\2\u0179\u017a\7\63\2\2\u017a\u017b\7\62"+
		"\2\2\u017b8\3\2\2\2\u017c\u017d\7n\2\2\u017d\u017e\7q\2\2\u017e\u017f"+
		"\7i\2\2\u017f:\3\2\2\2\u0180\u0181\7U\2\2\u0181\u0182\7]\2\2\u0182<\3"+
		"\2\2\2\u0183\u0184\7R\2\2\u0184\u0185\7]\2\2\u0185>\3\2\2\2\u0186\u0187"+
		"\7U\2\2\u0187@\3\2\2\2\u0188\u0189\7R\2\2\u0189B\3\2\2\2\u018a\u018b\5"+
		"\u010d\u0087\2\u018bD\3\2\2\2\u018c\u018d\7u\2\2\u018d\u018e\7k\2\2\u018e"+
		"\u018f\7p\2\2\u018fF\3\2\2\2\u0190\u0191\7e\2\2\u0191\u0192\7q\2\2\u0192"+
		"\u0193\7u\2\2\u0193H\3\2\2\2\u0194\u0195\7v\2\2\u0195\u0196\7c\2\2\u0196"+
		"\u0197\7p\2\2\u0197J\3\2\2\2\u0198\u0199\7c\2\2\u0199\u019a\7u\2\2\u019a"+
		"\u019b\7k\2\2\u019b\u019c\7p\2\2\u019cL\3\2\2\2\u019d\u019e\7c\2\2\u019e"+
		"\u019f\7e\2\2\u019f\u01a0\7q\2\2\u01a0\u01a1\7u\2\2\u01a1N\3\2\2\2\u01a2"+
		"\u01a3\7c\2\2\u01a3\u01a4\7v\2\2\u01a4\u01a5\7c\2\2\u01a5\u01a6\7p\2\2"+
		"\u01a6P\3\2\2\2\u01a7\u01a8\7c\2\2\u01a8\u01a9\7v\2\2\u01a9\u01aa\7c\2"+
		"\2\u01aa\u01ab\7p\2\2\u01ab\u01ac\7\64\2\2\u01acR\3\2\2\2\u01ad\u01ae"+
		"\7u\2\2\u01ae\u01af\7k\2\2\u01af\u01b0\7p\2\2\u01b0\u01b1\7j\2\2\u01b1"+
		"T\3\2\2\2\u01b2\u01b3\7e\2\2\u01b3\u01b4\7q\2\2\u01b4\u01b5\7u\2\2\u01b5"+
		"\u01b6\7j\2\2\u01b6V\3\2\2\2\u01b7\u01b8\7v\2\2\u01b8\u01b9\7c\2\2\u01b9"+
		"\u01ba\7p\2\2\u01ba\u01bb\7j\2\2\u01bbX\3\2\2\2\u01bc\u01bd\7c\2\2\u01bd"+
		"\u01be\7u\2\2\u01be\u01bf\7k\2\2\u01bf\u01c0\7p\2\2\u01c0\u01c1\7j\2\2"+
		"\u01c1Z\3\2\2\2\u01c2\u01c3\7c\2\2\u01c3\u01c4\7e\2\2\u01c4\u01c5\7q\2"+
		"\2\u01c5\u01c6\7u\2\2\u01c6\u01c7\7j\2\2\u01c7\\\3\2\2\2\u01c8\u01c9\7"+
		"c\2\2\u01c9\u01ca\7v\2\2\u01ca\u01cb\7c\2\2\u01cb\u01cc\7p\2\2\u01cc\u01cd"+
		"\7j\2\2\u01cd^\3\2\2\2\u01ce\u01d0\5#\22\2\u01cf\u01ce\3\2\2\2\u01cf\u01d0"+
		"\3\2\2\2\u01d0\u01d5\3\2\2\2\u01d1\u01d2\7r\2\2\u01d2\u01d6\7k\2\2\u01d3"+
		"\u01d4\7R\2\2\u01d4\u01d6\7K\2\2\u01d5\u01d1\3\2\2\2\u01d5\u01d3\3\2\2"+
		"\2\u01d6`\3\2\2\2\u01d7\u01d9\5#\22\2\u01d8\u01d7\3\2\2\2\u01d8\u01d9"+
		"\3\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01db\7G\2\2\u01dbb\3\2\2\2\u01dc\u01eb"+
		"\7\u00b2\2\2\u01dd\u01de\7f\2\2\u01de\u01df\7g\2\2\u01df\u01e0\7i\2\2"+
		"\u01e0\u01e1\7i\2\2\u01e1\u01e2\7t\2\2\u01e2\u01eb\7g\2\2\u01e3\u01e4"+
		"\7f\2\2\u01e4\u01e5\7g\2\2\u01e5\u01e6\7i\2\2\u01e6\u01e7\7i\2\2\u01e7"+
		"\u01e8\7t\2\2\u01e8\u01e9\7g\2\2\u01e9\u01eb\7u\2\2\u01ea\u01dc\3\2\2"+
		"\2\u01ea\u01dd\3\2\2\2\u01ea\u01e3\3\2\2\2\u01ebd\3\2\2\2\u01ec\u01ed"+
		"\7@\2\2\u01edf\3\2\2\2\u01ee\u01ef\7@\2\2\u01ef\u01f0\7?\2\2\u01f0h\3"+
		"\2\2\2\u01f1\u01f2\7>\2\2\u01f2j\3\2\2\2\u01f3\u01f4\7>\2\2\u01f4\u01f5"+
		"\7?\2\2\u01f5l\3\2\2\2\u01f6\u01f7\7?\2\2\u01f7n\3\2\2\2\u01f8\u01f9\7"+
		"#\2\2\u01f9\u01fd\7?\2\2\u01fa\u01fb\7>\2\2\u01fb\u01fd\7@\2\2\u01fc\u01f8"+
		"\3\2\2\2\u01fc\u01fa\3\2\2\2\u01fdp\3\2\2\2\u01fe\u0202\7\u0080\2\2\u01ff"+
		"\u0200\7\u00c4\2\2\u0200\u0202\7\u00ae\2\2\u0201\u01fe\3\2\2\2\u0201\u01ff"+
		"\3\2\2\2\u0202r\3\2\2\2\u0203\u0204\7w\2\2\u0204\u0205\7r\2\2\u0205t\3"+
		"\2\2\2\u0206\u0207\7f\2\2\u0207\u0208\7q\2\2\u0208\u0209\7y\2\2\u0209"+
		"\u020a\7p\2\2\u020av\3\2\2\2\u020b\u020c\7e\2\2\u020c\u020d\7g\2\2\u020d"+
		"\u020e\7k\2\2\u020e\u020f\7n\2\2\u020f\u0210\7k\2\2\u0210\u0211\7p\2\2"+
		"\u0211\u0212\7i\2\2\u0212x\3\2\2\2\u0213\u0214\7h\2\2\u0214\u0215\7n\2"+
		"\2\u0215\u0216\7q\2\2\u0216\u0217\7q\2\2\u0217\u0218\7t\2\2\u0218z\3\2"+
		"\2\2\u0219\u021a\7j\2\2\u021a\u021b\7c\2\2\u021b\u021c\7n\2\2\u021c\u021d"+
		"\7h\2\2\u021d\u021e\7W\2\2\u021e\u021f\7r\2\2\u021f|\3\2\2\2\u0220\u0221"+
		"\7j\2\2\u0221\u0222\7c\2\2\u0222\u0223\7n\2\2\u0223\u0224\7h\2\2\u0224"+
		"\u0225\7F\2\2\u0225\u0226\7q\2\2\u0226\u0227\7y\2\2\u0227\u0228\7p\2\2"+
		"\u0228~\3\2\2\2\u0229\u022a\7j\2\2\u022a\u022b\7c\2\2\u022b\u022c\7n\2"+
		"\2\u022c\u022d\7h\2\2\u022d\u022e\7G\2\2\u022e\u022f\7x\2\2\u022f\u0230"+
		"\7g\2\2\u0230\u0231\7p\2\2\u0231\u0080\3\2\2\2\u0232\u0233\7w\2\2\u0233"+
		"\u0234\7p\2\2\u0234\u0235\7p\2\2\u0235\u0236\7g\2\2\u0236\u0237\7e\2\2"+
		"\u0237\u0238\7g\2\2\u0238\u0239\7u\2\2\u0239\u023a\7u\2\2\u023a\u023b"+
		"\7c\2\2\u023b\u023c\7t\2\2\u023c\u023d\7{\2\2\u023d\u0082\3\2\2\2\u023e"+
		"\u0248\5\u008dG\2\u023f\u0248\5\u008fH\2\u0240\u0248\5\u0091I\2\u0241"+
		"\u0248\5\u0099M\2\u0242\u0248\5\u009bN\2\u0243\u0248\5\u009dO\2\u0244"+
		"\u0248\5\u00a5S\2\u0245\u0248\5\u00a7T\2\u0246\u0248\5\u00a9U\2\u0247"+
		"\u023e\3\2\2\2\u0247\u023f\3\2\2\2\u0247\u0240\3\2\2\2\u0247\u0241\3\2"+
		"\2\2\u0247\u0242\3\2\2\2\u0247\u0243\3\2\2\2\u0247\u0244\3\2\2\2\u0247"+
		"\u0245\3\2\2\2\u0247\u0246\3\2\2\2\u0248\u0084\3\2\2\2\u0249\u0253\5\u0093"+
		"J\2\u024a\u0253\5\u0095K\2\u024b\u0253\5\u0097L\2\u024c\u0253\5\u009f"+
		"P\2\u024d\u0253\5\u00a1Q\2\u024e\u0253\5\u00a3R\2\u024f\u0253\5\u00ab"+
		"V\2\u0250\u0253\5\u00adW\2\u0251\u0253\5\u00afX\2\u0252\u0249\3\2\2\2"+
		"\u0252\u024a\3\2\2\2\u0252\u024b\3\2\2\2\u0252\u024c\3\2\2\2\u0252\u024d"+
		"\3\2\2\2\u0252\u024e\3\2\2\2\u0252\u024f\3\2\2\2\u0252\u0250\3\2\2\2\u0252"+
		"\u0251\3\2\2\2\u0253\u0086\3\2\2\2\u0254\u0255\7e\2\2\u0255\u0256\7w\2"+
		"\2\u0256\u0257\7t\2\2\u0257\u0258\7t\2\2\u0258\u0259\7F\2\2\u0259\u025a"+
		"\7c\2\2\u025a\u025b\7v\2\2\u025b\u025c\7g\2\2\u025c\u0088\3\2\2\2\u025d"+
		"\u025e\7e\2\2\u025e\u025f\7w\2\2\u025f\u0260\7t\2\2\u0260\u0261\7t\2\2"+
		"\u0261\u0262\7V\2\2\u0262\u0263\7k\2\2\u0263\u0264\7o\2\2\u0264\u0265"+
		"\7g\2\2\u0265\u008a\3\2\2\2\u0266\u0267\7e\2\2\u0267\u0268\7w\2\2\u0268"+
		"\u0269\7t\2\2\u0269\u026a\7t\2\2\u026a\u026b\7F\2\2\u026b\u026c\7c\2\2"+
		"\u026c\u026d\7v\2\2\u026d\u026e\7g\2\2\u026e\u026f\7V\2\2\u026f\u0270"+
		"\7k\2\2\u0270\u0271\7o\2\2\u0271\u0272\7g\2\2\u0272\u008c\3\2\2\2\u0273"+
		"\u0274\7r\2\2\u0274\u0275\7n\2\2\u0275\u0276\7w\2\2\u0276\u0277\7u\2\2"+
		"\u0277\u0278\7F\2\2\u0278\u0279\7c\2\2\u0279\u027a\7{\2\2\u027a\u027b"+
		"\7u\2\2\u027b\u008e\3\2\2\2\u027c\u027d\7r\2\2\u027d\u027e\7n\2\2\u027e"+
		"\u027f\7w\2\2\u027f\u0280\7u\2\2\u0280\u0281\7O\2\2\u0281\u0282\7q\2\2"+
		"\u0282\u0283\7p\2\2\u0283\u0284\7v\2\2\u0284\u0285\7j\2\2\u0285\u0286"+
		"\7u\2\2\u0286\u0090\3\2\2\2\u0287\u0288\7r\2\2\u0288\u0289\7n\2\2\u0289"+
		"\u028a\7w\2\2\u028a\u028b\7u\2\2\u028b\u028c\7[\2\2\u028c\u028d\7g\2\2"+
		"\u028d\u028e\7c\2\2\u028e\u028f\7t\2\2\u028f\u0290\7u\2\2\u0290\u0092"+
		"\3\2\2\2\u0291\u0292\7r\2\2\u0292\u0293\7n\2\2\u0293\u0294\7w\2\2\u0294"+
		"\u0295\7u\2\2\u0295\u0296\7J\2\2\u0296\u0297\7q\2\2\u0297\u0298\7w\2\2"+
		"\u0298\u0299\7t\2\2\u0299\u029a\7u\2\2\u029a\u0094\3\2\2\2\u029b\u029c"+
		"\7r\2\2\u029c\u029d\7n\2\2\u029d\u029e\7w\2\2\u029e\u029f\7u\2\2\u029f"+
		"\u02a0\7O\2\2\u02a0\u02a1\7k\2\2\u02a1\u02a2\7p\2\2\u02a2\u02a3\7w\2\2"+
		"\u02a3\u02a4\7v\2\2\u02a4\u02a5\7g\2\2\u02a5\u02a6\7u\2\2\u02a6\u0096"+
		"\3\2\2\2\u02a7\u02a8\7r\2\2\u02a8\u02a9\7n\2\2\u02a9\u02aa\7w\2\2\u02aa"+
		"\u02ab\7u\2\2\u02ab\u02ac\7U\2\2\u02ac\u02ad\7g\2\2\u02ad\u02ae\7e\2\2"+
		"\u02ae\u02af\7q\2\2\u02af\u02b0\7p\2\2\u02b0\u02b1\7f\2\2\u02b1\u02b2"+
		"\7u\2\2\u02b2\u0098\3\2\2\2\u02b3\u02b4\7o\2\2\u02b4\u02b5\7k\2\2\u02b5"+
		"\u02b6\7p\2\2\u02b6\u02b7\7w\2\2\u02b7\u02b8\7u\2\2\u02b8\u02b9\7F\2\2"+
		"\u02b9\u02ba\7c\2\2\u02ba\u02bb\7{\2\2\u02bb\u02bc\7u\2\2\u02bc\u009a"+
		"\3\2\2\2\u02bd\u02be\7o\2\2\u02be\u02bf\7k\2\2\u02bf\u02c0\7p\2\2\u02c0"+
		"\u02c1\7w\2\2\u02c1\u02c2\7u\2\2\u02c2\u02c3\7O\2\2\u02c3\u02c4\7q\2\2"+
		"\u02c4\u02c5\7p\2\2\u02c5\u02c6\7v\2\2\u02c6\u02c7\7j\2\2\u02c7\u02c8"+
		"\7u\2\2\u02c8\u009c\3\2\2\2\u02c9\u02ca\7o\2\2\u02ca\u02cb\7k\2\2\u02cb"+
		"\u02cc\7p\2\2\u02cc\u02cd\7w\2\2\u02cd\u02ce\7u\2\2\u02ce\u02cf\7[\2\2"+
		"\u02cf\u02d0\7g\2\2\u02d0\u02d1\7c\2\2\u02d1\u02d2\7t\2\2\u02d2\u02d3"+
		"\7u\2\2\u02d3\u009e\3\2\2\2\u02d4\u02d5\7o\2\2\u02d5\u02d6\7k\2\2\u02d6"+
		"\u02d7\7p\2\2\u02d7\u02d8\7w\2\2\u02d8\u02d9\7u\2\2\u02d9\u02da\7J\2\2"+
		"\u02da\u02db\7q\2\2\u02db\u02dc\7w\2\2\u02dc\u02dd\7t\2\2\u02dd\u02de"+
		"\7u\2\2\u02de\u00a0\3\2\2\2\u02df\u02e0\7o\2\2\u02e0\u02e1\7k\2\2\u02e1"+
		"\u02e2\7p\2\2\u02e2\u02e3\7w\2\2\u02e3\u02e4\7u\2\2\u02e4\u02e5\7O\2\2"+
		"\u02e5\u02e6\7k\2\2\u02e6\u02e7\7p\2\2\u02e7\u02e8\7w\2\2\u02e8\u02e9"+
		"\7v\2\2\u02e9\u02ea\7g\2\2\u02ea\u02eb\7u\2\2\u02eb\u00a2\3\2\2\2\u02ec"+
		"\u02ed\7o\2\2\u02ed\u02ee\7k\2\2\u02ee\u02ef\7p\2\2\u02ef\u02f0\7w\2\2"+
		"\u02f0\u02f1\7u\2\2\u02f1\u02f2\7U\2\2\u02f2\u02f3\7g\2\2\u02f3\u02f4"+
		"\7e\2\2\u02f4\u02f5\7q\2\2\u02f5\u02f6\7p\2\2\u02f6\u02f7\7f\2\2\u02f7"+
		"\u02f8\7u\2\2\u02f8\u00a4\3\2\2\2\u02f9\u02fa\7y\2\2\u02fa\u02fb\7k\2"+
		"\2\u02fb\u02fc\7v\2\2\u02fc\u02fd\7j\2\2\u02fd\u02fe\7F\2\2\u02fe\u02ff"+
		"\7c\2\2\u02ff\u0300\7{\2\2\u0300\u0301\7u\2\2\u0301\u00a6\3\2\2\2\u0302"+
		"\u0303\7y\2\2\u0303\u0304\7k\2\2\u0304\u0305\7v\2\2\u0305\u0306\7j\2\2"+
		"\u0306\u0307\7O\2\2\u0307\u0308\7q\2\2\u0308\u0309\7p\2\2\u0309\u030a"+
		"\7v\2\2\u030a\u030b\7j\2\2\u030b\u030c\7u\2\2\u030c\u00a8\3\2\2\2\u030d"+
		"\u030e\7y\2\2\u030e\u030f\7k\2\2\u030f\u0310\7v\2\2\u0310\u0311\7j\2\2"+
		"\u0311\u0312\7[\2\2\u0312\u0313\7g\2\2\u0313\u0314\7c\2\2\u0314\u0315"+
		"\7t\2\2\u0315\u0316\7u\2\2\u0316\u00aa\3\2\2\2\u0317\u0318\7y\2\2\u0318"+
		"\u0319\7k\2\2\u0319\u031a\7v\2\2\u031a\u031b\7j\2\2\u031b\u031c\7J\2\2"+
		"\u031c\u031d\7q\2\2\u031d\u031e\7w\2\2\u031e\u031f\7t\2\2\u031f\u0320"+
		"\7u\2\2\u0320\u00ac\3\2\2\2\u0321\u0322\7y\2\2\u0322\u0323\7k\2\2\u0323"+
		"\u0324\7v\2\2\u0324\u0325\7j\2\2\u0325\u0326\7O\2\2\u0326\u0327\7k\2\2"+
		"\u0327\u0328\7p\2\2\u0328\u0329\7w\2\2\u0329\u032a\7v\2\2\u032a\u032b"+
		"\7g\2\2\u032b\u032c\7u\2\2\u032c\u00ae\3\2\2\2\u032d\u032e\7y\2\2\u032e"+
		"\u032f\7k\2\2\u032f\u0330\7v\2\2\u0330\u0331\7j\2\2\u0331\u0332\7U\2\2"+
		"\u0332\u0333\7g\2\2\u0333\u0334\7e\2\2\u0334\u0335\7q\2\2\u0335\u0336"+
		"\7p\2\2\u0336\u0337\7f\2\2\u0337\u0338\7u\2\2\u0338\u00b0\3\2\2\2\u0339"+
		"\u033a\7*\2\2\u033a\u00b2\3\2\2\2\u033b\u033c\7+\2\2\u033c\u00b4\3\2\2"+
		"\2\u033d\u033e\7]\2\2\u033e\u00b6\3\2\2\2\u033f\u0340\7_\2\2\u0340\u00b8"+
		"\3\2\2\2\u0341\u0342\7)\2\2\u0342\u00ba\3\2\2\2\u0343\u0344\7<\2\2\u0344"+
		"\u0345\7?\2\2\u0345\u00bc\3\2\2\2\u0346\u0347\7.\2\2\u0347\u00be\3\2\2"+
		"\2\u0348\u0349\7=\2\2\u0349\u00c0\3\2\2\2\u034a\u034b\7\60\2\2\u034b\u00c2"+
		"\3\2\2\2\u034c\u034d\7e\2\2\u034d\u034e\7q\2\2\u034e\u034f\7p\2\2\u034f"+
		"\u0350\7v\2\2\u0350\u0351\7c\2\2\u0351\u0352\7k\2\2\u0352\u0353\7p\2\2"+
		"\u0353\u0354\7u\2\2\u0354\u00c4\3\2\2\2\u0355\u0356\7&\2\2\u0356\u0357"+
		"\7\60\2\2\u0357\u0358\3\2\2\2\u0358\u0359\5\u0109\u0085\2\u0359\u00c6"+
		"\3\2\2\2\u035a\u035b\7h\2\2\u035b\u035c\7\60\2\2\u035c\u00c8\3\2\2\2\u035d"+
		"\u035e\5\u00f1y\2\u035e\u00ca\3\2\2\2\u035f\u0360\5\u00f3z\2\u0360\u0361"+
		"\5\u00f1y\2\u0361\u00cc\3\2\2\2\u0362\u0366\5\u00b9]\2\u0363\u0365\n\2"+
		"\2\2\u0364\u0363\3\2\2\2\u0365\u0368\3\2\2\2\u0366\u0364\3\2\2\2\u0366"+
		"\u0367\3\2\2\2\u0367\u0369\3\2\2\2\u0368\u0366\3\2\2\2\u0369\u036a\5\u00b9"+
		"]\2\u036a\u00ce\3\2\2\2\u036b\u036f\5\u00f5{\2\u036c\u036f\5\u00f9}\2"+
		"\u036d\u036f\5\u00fb~\2\u036e\u036b\3\2\2\2\u036e\u036c\3\2\2\2\u036e"+
		"\u036d\3\2\2\2\u036f\u00d0\3\2\2\2\u0370\u0371\5\u00f7|\2\u0371\u00d2"+
		"\3\2\2\2\u0372\u0373\5\u0101\u0081\2\u0373\u0374\5\u0101\u0081\2\u0374"+
		"\u0375\5\u0101\u0081\2\u0375\u0376\5\u0101\u0081\2\u0376\u0377\5#\22\2"+
		"\u0377\u0378\5\u00ebv\2\u0378\u0379\5#\22\2\u0379\u037a\5\u00e9u\2\u037a"+
		"\u00d4\3\2\2\2\u037b\u037c\5\u00edw\2\u037c\u037d\5\u0103\u0082\2\u037d"+
		"\u0381\5\u00efx\2\u037e\u037f\5\u0103\u0082\2\u037f\u0380\5\u00efx\2\u0380"+
		"\u0382\3\2\2\2\u0381\u037e\3\2\2\2\u0381\u0382\3\2\2\2\u0382\u00d6\3\2"+
		"\2\2\u0383\u0384\5\u00d3j\2\u0384\u0385\7V\2\2\u0385\u0386\5\u00d5k\2"+
		"\u0386\u00d8\3\2\2\2\u0387\u0388\7>\2\2\u0388\u0389\7d\2\2\u0389\u038a"+
		"\7q\2\2\u038a\u038b\7q\2\2\u038b\u038c\7n\2\2\u038c\u038d\7@\2\2\u038d"+
		"\u00da\3\2\2\2\u038e\u038f\7>\2\2\u038f\u0390\7p\2\2\u0390\u0391\7w\2"+
		"\2\u0391\u0392\7o\2\2\u0392\u0393\7d\2\2\u0393\u0394\7g\2\2\u0394\u0395"+
		"\7t\2\2\u0395\u0396\7@\2\2\u0396\u00dc\3\2\2\2\u0397\u0398\7>\2\2\u0398"+
		"\u0399\7v\2\2\u0399\u039a\7g\2\2\u039a\u039b\7z\2\2\u039b\u039c\7v\2\2"+
		"\u039c\u039d\7@\2\2\u039d\u00de\3\2\2\2\u039e\u039f\7>\2\2\u039f\u03a0"+
		"\7f\2\2\u03a0\u03a1\7c\2\2\u03a1\u03a2\7v\2\2\u03a2\u03a3\7g\2\2\u03a3"+
		"\u03a4\7@\2\2\u03a4\u00e0\3\2\2\2\u03a5\u03a6\7>\2\2\u03a6\u03a7\7v\2"+
		"\2\u03a7\u03a8\7k\2\2\u03a8\u03a9\7o\2\2\u03a9\u03aa\7g\2\2\u03aa\u03ab"+
		"\7@\2\2\u03ab\u00e2\3\2\2\2\u03ac\u03ad\7>\2\2\u03ad\u03ae\7f\2\2\u03ae"+
		"\u03af\7c\2\2\u03af\u03b0\7v\2\2\u03b0\u03b1\7g\2\2\u03b1\u03b2\7v\2\2"+
		"\u03b2\u03b3\7k\2\2\u03b3\u03b4\7o\2\2\u03b4\u03b5\7g\2\2\u03b5\u03b6"+
		"\7@\2\2\u03b6\u00e4\3\2\2\2\u03b7\u03b8\7>\2\2\u03b8\u03b9\7n\2\2\u03b9"+
		"\u03ba\7k\2\2\u03ba\u03bb\7u\2\2\u03bb\u03bc\7v\2\2\u03bc\u03bd\7@\2\2"+
		"\u03bd\u00e6\3\2\2\2\u03be\u03c3\5\u00cdg\2\u03bf\u03c3\5\u00d3j\2\u03c0"+
		"\u03c3\5\u00d5k\2\u03c1\u03c3\5\u00d7l\2\u03c2\u03be\3\2\2\2\u03c2\u03bf"+
		"\3\2\2\2\u03c2\u03c0\3\2\2\2\u03c2\u03c1\3\2\2\2\u03c3\u00e8\3\2\2\2\u03c4"+
		"\u03c5\7\62\2\2\u03c5\u03cb\4\63;\2\u03c6\u03c7\4\63\64\2\u03c7\u03cb"+
		"\5\u0101\u0081\2\u03c8\u03c9\7\65\2\2\u03c9\u03cb\4\62\63\2\u03ca\u03c4"+
		"\3\2\2\2\u03ca\u03c6\3\2\2\2\u03ca\u03c8\3\2\2\2\u03cb\u00ea\3\2\2\2\u03cc"+
		"\u03cd\7\62\2\2\u03cd\u03d1\4\63;\2\u03ce\u03cf\7\63\2\2\u03cf\u03d1\4"+
		"\62\64\2\u03d0\u03cc\3\2\2\2\u03d0\u03ce\3\2\2\2\u03d1\u00ec\3\2\2\2\u03d2"+
		"\u03d3\4\62\63\2\u03d3\u03d7\5\u0101\u0081\2\u03d4\u03d5\7\64\2\2\u03d5"+
		"\u03d7\4\62\65\2\u03d6\u03d2\3\2\2\2\u03d6\u03d4\3\2\2\2\u03d7\u00ee\3"+
		"\2\2\2\u03d8\u03d9\4\62\67\2\u03d9\u03da\5\u0101\u0081\2\u03da\u00f0\3"+
		"\2\2\2\u03db\u03df\t\3\2\2\u03dc\u03de\t\4\2\2\u03dd\u03dc\3\2\2\2\u03de"+
		"\u03e1\3\2\2\2\u03df\u03dd\3\2\2\2\u03df\u03e0\3\2\2\2\u03e0\u00f2\3\2"+
		"\2\2\u03e1\u03df\3\2\2\2\u03e2\u03e3\7/\2\2\u03e3\u00f4\3\2\2\2\u03e4"+
		"\u03e6\5\u00f3z\2\u03e5\u03e4\3\2\2\2\u03e5\u03e6\3\2\2\2\u03e6\u03e8"+
		"\3\2\2\2\u03e7\u03e9\t\5\2\2\u03e8\u03e7\3\2\2\2\u03e9\u03ea\3\2\2\2\u03ea"+
		"\u03e8\3\2\2\2\u03ea\u03eb\3\2\2\2\u03eb\u03f2\3\2\2\2\u03ec\u03ee\7\60"+
		"\2\2\u03ed\u03ef\t\5\2\2\u03ee\u03ed\3\2\2\2\u03ef\u03f0\3\2\2\2\u03f0"+
		"\u03ee\3\2\2\2\u03f0\u03f1\3\2\2\2\u03f1\u03f3\3\2\2\2\u03f2\u03ec\3\2"+
		"\2\2\u03f2\u03f3\3\2\2\2\u03f3\u00f6\3\2\2\2\u03f4\u03f6\t\5\2\2\u03f5"+
		"\u03f4\3\2\2\2\u03f6\u03f7\3\2\2\2\u03f7\u03f5\3\2\2\2\u03f7\u03f8\3\2"+
		"\2\2\u03f8\u00f8\3\2\2\2\u03f9\u03fb\7\62\2\2\u03fa\u03fc\4\629\2\u03fb"+
		"\u03fa\3\2\2\2\u03fc\u03fd\3\2\2\2\u03fd\u03fb\3\2\2\2\u03fd\u03fe\3\2"+
		"\2\2\u03fe\u00fa\3\2\2\2\u03ff\u0400\7\62\2\2\u0400\u0401\7z\2\2\u0401"+
		"\u0403\3\2\2\2\u0402\u0404\t\6\2\2\u0403\u0402\3\2\2\2\u0404\u0405\3\2"+
		"\2\2\u0405\u0403\3\2\2\2\u0405\u0406\3\2\2\2\u0406\u00fc\3\2\2\2\u0407"+
		"\u0408\t\3\2\2\u0408\u00fe\3\2\2\2\u0409\u040d\t\7\2\2\u040a\u040d\5\u0101"+
		"\u0081\2\u040b\u040d\7a\2\2\u040c\u0409\3\2\2\2\u040c\u040a\3\2\2\2\u040c"+
		"\u040b\3\2\2\2\u040d\u0100\3\2\2\2\u040e\u040f\4\62;\2\u040f\u0102\3\2"+
		"\2\2\u0410\u0411\7<\2\2\u0411\u0104\3\2\2\2\u0412\u0413\7\61\2\2\u0413"+
		"\u0106\3\2\2\2\u0414\u0415\7/\2\2\u0415\u0416\7@\2\2\u0416\u0108\3\2\2"+
		"\2\u0417\u0418\7^\2\2\u0418\u041b\t\b\2\2\u0419\u041b\n\t\2\2\u041a\u0417"+
		"\3\2\2\2\u041a\u0419\3\2\2\2\u041b\u041c\3\2\2\2\u041c\u041a\3\2\2\2\u041c"+
		"\u041d\3\2\2\2\u041d\u010a\3\2\2\2\u041e\u041f\7^\2\2\u041f\u0422\t\b"+
		"\2\2\u0420\u0422\n\n\2\2\u0421\u041e\3\2\2\2\u0421\u0420\3\2\2\2\u0422"+
		"\u0423\3\2\2\2\u0423\u0421\3\2\2\2\u0423\u0424\3\2\2\2\u0424\u010c\3\2"+
		"\2\2\u0425\u0426\7/\2\2\u0426\u0427\7*\2\2\u0427\u010e\3\2\2\2\u0428\u0429"+
		"\7\61\2\2\u0429\u042a\7\61\2\2\u042a\u042e\3\2\2\2\u042b\u042d\n\13\2"+
		"\2\u042c\u042b\3\2\2\2\u042d\u0430\3\2\2\2\u042e\u042c\3\2\2\2\u042e\u042f"+
		"\3\2\2\2\u042f\u0432\3\2\2\2\u0430\u042e\3\2\2\2\u0431\u0433\7\17\2\2"+
		"\u0432\u0431\3\2\2\2\u0432\u0433\3\2\2\2\u0433\u0434\3\2\2\2\u0434\u0442"+
		"\7\f\2\2\u0435\u0436\7\61\2\2\u0436\u0437\7,\2\2\u0437\u043b\3\2\2\2\u0438"+
		"\u043a\13\2\2\2\u0439\u0438\3\2\2\2\u043a\u043d\3\2\2\2\u043b\u043c\3"+
		"\2\2\2\u043b\u0439\3\2\2\2\u043c\u043e\3\2\2\2\u043d\u043b\3\2\2\2\u043e"+
		"\u043f\7,\2\2\u043f\u0442\7\61\2\2\u0440\u0442\7\2\2\3\u0441\u0428\3\2"+
		"\2\2\u0441\u0435\3\2\2\2\u0441\u0440\3\2\2\2\u0442\u0443\3\2\2\2\u0443"+
		"\u0444\b\u0088\2\2\u0444\u0110\3\2\2\2\u0445\u0447\t\f\2\2\u0446\u0445"+
		"\3\2\2\2\u0447\u0448\3\2\2\2\u0448\u0446\3\2\2\2\u0448\u0449\3\2\2\2\u0449"+
		"\u044a\3\2\2\2\u044a\u044b\b\u0089\3\2\u044b\u0112\3\2\2\2%\2\u0169\u01cf"+
		"\u01d5\u01d8\u01ea\u01fc\u0201\u0247\u0252\u0366\u036e\u0381\u03c2\u03ca"+
		"\u03d0\u03d6\u03df\u03e5\u03ea\u03f0\u03f2\u03f7\u03fd\u0405\u040c\u041a"+
		"\u041c\u0421\u0423\u042e\u0432\u043b\u0441\u0448\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}