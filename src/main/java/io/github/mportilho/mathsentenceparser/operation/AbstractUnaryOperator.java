package io.github.mportilho.mathsentenceparser.operation;

public abstract class AbstractUnaryOperator extends AbstractOperation {

	private AbstractOperation operand;

	protected AbstractOperation getOperand() {
		return operand;
	}
}
