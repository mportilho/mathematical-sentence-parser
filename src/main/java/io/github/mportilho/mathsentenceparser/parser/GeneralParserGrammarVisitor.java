package io.github.mportilho.mathsentenceparser.parser;

import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarBaseVisitor;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class GeneralParserGrammarVisitor extends MathematicalSentenceParserGrammarBaseVisitor<AbstractOperation> {

	private OperationSyntaxTreeContext context;

	public GeneralParserGrammarVisitor(OperationSyntaxTreeContext context) {
		super();
		this.context = context;
	}

}
