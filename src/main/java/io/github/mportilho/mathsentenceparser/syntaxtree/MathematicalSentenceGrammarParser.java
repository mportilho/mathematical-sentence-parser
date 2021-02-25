/*MIT License

Copyright (c) 2021 Marcelo Portilho

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/

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
