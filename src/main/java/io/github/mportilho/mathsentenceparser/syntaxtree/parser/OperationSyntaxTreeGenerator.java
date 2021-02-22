package io.github.mportilho.mathsentenceparser.syntaxtree.parser;

import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarParser.StartContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.OperationSyntaxTree;

public interface OperationSyntaxTreeGenerator {

	OperationSyntaxTree createOperationSyntaxTree(StartContext startContext);

}
