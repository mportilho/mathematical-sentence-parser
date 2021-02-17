package io.github.mportilho.mathsentenceparser.operation;

import io.github.mportilho.mathsentenceparser.ParsingContext;

public class AbstractBinaryOperationImpl extends AbstractBinaryOperation {

	public AbstractBinaryOperationImpl(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public Object resolve(ParsingContext context) {
		return null;
	}

}
