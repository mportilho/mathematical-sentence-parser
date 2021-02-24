package io.github.mportilho.mathsentenceparser.syntaxtree;

import java.util.HashSet;
import java.util.Set;

import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractUnaryOperator;
import io.github.mportilho.mathsentenceparser.operation.BaseOperation;
import io.github.mportilho.mathsentenceparser.operation.datetime.AbstractDateTimeOperation;
import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.other.DecisionOperation;
import io.github.mportilho.mathsentenceparser.operation.other.FunctionOperation;
import io.github.mportilho.mathsentenceparser.operation.other.JsonPathOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseProductOfSequenceOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseSummationOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.AbstractConstantValueOperation;
import io.github.mportilho.mathsentenceparser.operation.value.variable.AbstractVariableValueOperation;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public class CacheCheckVisitor implements OperationVisitor<Integer> {

	private Set<AbstractVariableValueOperation<?>> visitedVariables = new HashSet<>();

	public CacheCheckVisitor reset() {
		visitedVariables = new HashSet<>();
		return this;
	}

	private Integer checkCache(AbstractOperation operation) {
		if (operation.getCache() != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public Integer visit(BaseOperation operation) {
		Integer numberOfCaches = checkCache(operation);
		for (AssignedVariableOperation assignedVariableOperation : operation.getAssignedVariables().values()) {
			numberOfCaches += assignedVariableOperation.accept(this);
		}
		if (operation.getOperation() != null) {
			numberOfCaches += operation.getOperation().accept(this);
		}
		return numberOfCaches;
	}

	@Override
	public Integer visit(AbstractUnaryOperator operation) {
		Integer numberOfCaches = checkCache(operation);
		numberOfCaches += operation.getOperand().accept(this);
		return numberOfCaches;
	}

	@Override
	public Integer visit(AbstractBinaryOperation operation) {
		Integer numberOfCaches = checkCache(operation);
		numberOfCaches += operation.getLeftOperand().accept(this);
		numberOfCaches += operation.getRightOperand().accept(this);
		return numberOfCaches;
	}

	@Override
	public Integer visit(AbstractDateTimeOperation operation) {
		Integer numberOfCaches = checkCache(operation);
		numberOfCaches += operation.getLeftOperand().accept(this);
		numberOfCaches += operation.getRightOperand().accept(this);
		return numberOfCaches;
	}

	@Override
	public Integer visit(DecisionOperation operation) {
		Integer numberOfCaches = checkCache(operation);
		for (AbstractOperation op : operation.getOperations()) {
			numberOfCaches += op.accept(this);
		}
		return numberOfCaches;
	}

	@Override
	public Integer visit(FunctionOperation operation) {
		Integer numberOfCaches = checkCache(operation);
		for (AbstractOperation op : operation.getParameters()) {
			numberOfCaches += op.accept(this);
		}
		return numberOfCaches;
	}

	@Override
	public Integer visit(JsonPathOperation operation) {
		Integer numberOfCaches = checkCache(operation);
//		numberOfCaches += operation.getOperand().accept(this);
		return numberOfCaches;
	}

	@Override
	public Integer visit(PreciseSummationOperation operation) {
		Integer numberOfCaches = checkCache(operation);
		numberOfCaches += operation.getStartIndex().accept(this);
		numberOfCaches += operation.getEndIndex().accept(this);
		numberOfCaches += operation.getOperation().accept(this);
		return numberOfCaches;
	}

	@Override
	public Integer visit(PreciseProductOfSequenceOperation operation) {
		Integer numberOfCaches = checkCache(operation);
		numberOfCaches += operation.getStartIndex().accept(this);
		numberOfCaches += operation.getEndIndex().accept(this);
		numberOfCaches += operation.getOperation().accept(this);
		return numberOfCaches;
	}

	@Override
	public Integer visit(AbstractConstantValueOperation operation) {
		return checkCache(operation);
	}

	@Override
	public Integer visit(AbstractVariableValueOperation<?> operation) {
		// don't revisit same variables
		if (visitedVariables.contains(operation)) {
			return 0;
		} else {
			Integer numberOfCaches = checkCache(operation);
			visitedVariables.add(operation);
			if (operation.getProvidedValue() instanceof AbstractOperation) {
				numberOfCaches += ((AbstractOperation) operation.getProvidedValue()).accept(this);
			}
			return numberOfCaches;
		}
	}

}
