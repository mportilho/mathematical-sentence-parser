package io.github.mportilho.mathsentenceparser.parser;

import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarBaseVisitor;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;

public class LogicParserGrammarVisitor extends MathematicalSentenceParserGrammarBaseVisitor<AbstractOperation> {

	private ParserGrammarBaseVisitorContext context;

	public LogicParserGrammarVisitor(ParserGrammarBaseVisitorContext context) {
		super();
		this.context = context;
	}

}
