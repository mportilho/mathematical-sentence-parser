package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;

import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

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

	@Override
	protected Object resolve(OperationContext context) {
		return getLeftOperand().<BigDecimal>evaluate(context).multiply(getRightOperand().<BigDecimal>evaluate(context), context.getMathContext());
	}

	@Override
	protected String getOperationToken() {
		return "*";
	}

	public boolean isImplicit() {
		return implicit;
	}

	@Override
	public void composeTextualRepresentation(StringBuilder builder) {
		if (implicit) {
			boolean isVariableWithCache = AbstractVariableValueOperation.class.isAssignableFrom(getRightOperand().getClass())
					&& getRightOperand().getCache() != null;
			boolean isOperationNotApplyingParenthesis = !AbstractVariableValueOperation.class.isAssignableFrom(getRightOperand().getClass())
					&& !getRightOperand().isApplyingParenthesis();

			getLeftOperand().generateRepresentation(builder);
			if (isVariableWithCache || isOperationNotApplyingParenthesis) {
				builder.append('(');
			}
			getRightOperand().generateRepresentation(builder);
			if (isVariableWithCache || isOperationNotApplyingParenthesis) {
				builder.append(')');
			}
		} else {
			super.composeTextualRepresentation(builder);
		}
	}

}
