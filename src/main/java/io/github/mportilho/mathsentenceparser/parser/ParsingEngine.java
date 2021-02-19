package io.github.mportilho.mathsentenceparser.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;

import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarLexer;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarParser;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarParser.StartContext;

public class ParsingEngine {

	private OperationSyntaxTreeGenerator operationSyntaxTreeGenerator;

	public ParsingEngine(OperationSyntaxTreeGenerator operationSyntaxTreeGenerator) {
		this.operationSyntaxTreeGenerator = operationSyntaxTreeGenerator;
	}

	public OperationSyntaxTree parseSentence(CharStream expression) {
		StartContext startContext = createOperationParser(expression, PredictionMode.SLL).start();
		return operationSyntaxTreeGenerator.createOperationSyntaxTree(startContext);
	}

	public static void validate(CharStream expression) {
		createOperationParser(expression, PredictionMode.LL_EXACT_AMBIG_DETECTION).start();
	}

	private static MathematicalSentenceParserGrammarParser createOperationParser(CharStream expression, PredictionMode predictionMode) {
		MathematicalSentenceParserGrammarLexer lexer = new MathematicalSentenceParserGrammarLexer(expression);
		lexer.removeErrorListeners();
		lexer.addErrorListener(ParsingErrorListener.INSTANCE);
		MathematicalSentenceParserGrammarParser parser = new MathematicalSentenceParserGrammarParser(new CommonTokenStream(lexer));
		try {
			parser.getInterpreter().setPredictionMode(predictionMode);
			parser.removeErrorListeners();
			parser.addErrorListener(ParsingErrorListener.INSTANCE);
			return parser;
		} catch (Exception e) {
			throw new IllegalStateException("Error during expression parsing", e);
		}
	}

}
