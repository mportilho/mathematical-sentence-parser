package io.github.mportilho.mathsentenceparser.syntaxtree.visitor;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.datetime.AbstractDateTimeOperation;
import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.other.DecisionOperation;
import io.github.mportilho.mathsentenceparser.operation.other.BaseOperation;
import io.github.mportilho.mathsentenceparser.operation.other.FunctionOperation;
import io.github.mportilho.mathsentenceparser.operation.other.JsonPathOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseProductOfSequenceOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseSummationOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.AbstractConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;

public class WarmUpOperationVisitor implements OperationVisitor<Object> {

	private OperationContext context;

	public WarmUpOperationVisitor(OperationContext context) {
		this.context = context;
	}

	@Override
	public Object visit(BaseOperation operation) {
		if (nonNull(operation.getAssignedVariables())) {
			for (AbstractOperation abstractoperation : operation.getAssignedVariables().values()) {
				abstractoperation.accept(this);
			}
		}
		return operation.evaluate(context);
	}

	@Override
	public Object visit(AbstractUnaryOperator operation) {
		Object result = operation.getOperand().accept(this);
		return isNull(result) ? null : operation.evaluate(context);
	}

	@Override
	public Object visit(AbstractBinaryOperation operation) {
		Object left = operation.getLeftOperand().accept(this);
		Object right = operation.getRightOperand().accept(this);
		return (isNull(left) || isNull(right)) ? null : operation.evaluate(context);
	}

	@Override
	public Object visit(AbstractDateTimeOperation operation) {
		Object left = operation.getLeftOperand().accept(this);
		Object right = operation.getRightOperand().accept(this);
		return (isNull(left) || isNull(right)) ? null : operation.evaluate(context);
	}

	@Override
	public Object visit(DecisionOperation operation) {
		for (AbstractOperation abstractoperation : operation.getOperations()) {
			abstractoperation.accept(this);
		}
		try {
			return operation.evaluate(context);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Object visit(FunctionOperation operation) {
		boolean hasNull = false;
		for (AbstractOperation exp : operation.getParameters()) {
			if (exp.accept(this) == null) {
				hasNull = true;
			}
		}
		return hasNull ? null : operation.evaluate(context);
	}

	@Override
	public Object visit(JsonPathOperation operation) {
//		Object result = operation.getVariable().accept(this);
//		return isNull(result) ? null : operation.evaluate(context);
		return null;
	}

	@Override
	public Object visit(PreciseSummationOperation operation) {
		Object startIndexResult = operation.getStartIndex().accept(this);
		Object endIndexResult = operation.getEndIndex().accept(this);
		Object result = operation.getOperation().accept(this);
		return (isNull(startIndexResult) || isNull(endIndexResult) || isNull(result)) ? null : operation.evaluate(context);
	}

	@Override
	public Object visit(PreciseProductOfSequenceOperation operation) {
		Object startIndexResult = operation.getStartIndex().accept(this);
		Object endIndexResult = operation.getEndIndex().accept(this);
		Object result = operation.getOperation().accept(this);
		return (isNull(startIndexResult) || isNull(endIndexResult) || isNull(result)) ? null : operation.evaluate(context);
	}

	@Override
	public Object visit(AssignedVariableOperation operation) {
		Object result = operation.accept(this);
		return isNull(result) ? null : operation.evaluate(context);
	}

	@Override
	public Object visit(AbstractConstantValueOperation operation) {
		return operation.evaluate(context);
	}

	@Override
	public Object visit(AbstractVariableValueOperation operation) {
		return operation.evaluate(context);
	}

}
