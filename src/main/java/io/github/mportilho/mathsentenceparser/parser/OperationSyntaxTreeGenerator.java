package io.github.mportilho.mathsentenceparser.parser;

import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarParser.StartContext;

public interface OperationSyntaxTreeGenerator {

	public OperationSyntaxTree createOperationSyntaxTree(StartContext startContext);

}
