package io.github.mportilho.mathsentenceparser.parser;

import static java.util.Objects.nonNull;

import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarBaseVisitor;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarParser.AssignmentExpressionContext;
import io.github.mportilho.mathsentenceparser.grammar.MathematicalSentenceParserGrammarParser.LogicalStartContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.other.EmptyOperation;
import io.github.mportilho.mathsentenceparser.operation.value.OperationValueType;

public class LogicParserGrammarVisitor extends MathematicalSentenceParserGrammarBaseVisitor<AbstractOperation> {

	private OperationSyntaxTreeContext context;

	public LogicParserGrammarVisitor(OperationSyntaxTreeContext context) {
		super();
		this.context = context;
	}

	@Override
	public AbstractOperation visitLogicalStart(LogicalStartContext ctx) {
		AbstractOperation previousAssignmentOperation = null;
		for (AssignmentExpressionContext expressionContext : ctx.assignmentExpression()) {
			AbstractOperation assignmentOperation = expressionContext.accept(this);
			assignmentOperation.addParent(previousAssignmentOperation);
			previousAssignmentOperation = assignmentOperation;
		}
		AbstractOperation logicalOperation = nonNull(ctx.logicalExpression()) ? ctx.logicalExpression().accept(this)
				: new EmptyOperation(OperationValueType.BOOLEAN, context.getAssignedVariables());
		logicalOperation.addParent(previousAssignmentOperation);
		return logicalOperation;
	}

}
