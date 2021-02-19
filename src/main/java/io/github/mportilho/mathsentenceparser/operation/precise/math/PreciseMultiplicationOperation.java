package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;

public class PreciseMultiplicationOperation extends AbstractBinaryOperation {

	private boolean implicit = false;

	public PreciseMultiplicationOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	public PreciseMultiplicationOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, boolean implicit) {
		super(leftOperand, rightOperand);
		this.implicit = implicit;
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		PreciseMultiplicationOperation operation = (PreciseMultiplicationOperation) super.createClone(context);
		operation.implicit = this.implicit;
		return operation;
	}

	protected void implicit(boolean implicit) {
		this.implicit = implicit;
	}

	@Override
	protected Object resolve(OperationContext context) {
		return getLeftOperand().<BigDecimal>evaluate(context).multiply(getRightOperand().<BigDecimal>evaluate(context), context.getMathContext());
	}

	public boolean isImplicit() {
		return implicit;
	}

	@Override
	protected String getOperationToken() {
		return "*";
	}

	@Override
	public String toString() {
		return implicit ? getLeftOperand().toString() + getRightOperand().toString() : super.toString();
	}

}
