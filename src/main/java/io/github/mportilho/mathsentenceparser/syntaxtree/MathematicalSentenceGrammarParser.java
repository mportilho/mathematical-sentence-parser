package io.github.mportilho.mathsentenceparser.syntaxtree;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;

import io.github.mportilho.mathsentenceparser.MathSentenceOptions;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarLexer;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarParser;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarParser.StartContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.parser.ParsingErrorListener;

public final class MathematicalSentenceGrammarParser {

	public static final OperationSyntaxTree parseSentence(CharStream expression, MathSentenceOptions mathSentenceOptions) {
		StartContext startContext = createOperationParser(expression, PredictionMode.SLL).start();
		OperationSyntaxTree operationSyntaxTree = mathSentenceOptions.getOperationSyntaxTreeGenerator().createOperationSyntaxTree(startContext);
		operationSyntaxTree.setOperationContext(new OperationContext(mathSentenceOptions.getMathContext(), mathSentenceOptions.getScale(), false));
		return operationSyntaxTree;
	}

	public static final void validate(CharStream expression) {
		createOperationParser(expression, PredictionMode.LL_EXACT_AMBIG_DETECTION).start();
	}

	private static final MathematicalSentenceParserGrammarParser createOperationParser(CharStream expression, PredictionMode predictionMode) {
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
