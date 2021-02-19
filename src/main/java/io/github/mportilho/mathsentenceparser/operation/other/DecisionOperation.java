package io.github.mportilho.mathsentenceparser.operation.other;

import java.util.ArrayList;
import java.util.List;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.parser.OperationVisitor;

public class DecisionOperation extends AbstractOperation {

	private List<AbstractOperation> operations;

	public DecisionOperation(List<AbstractOperation> operations) {
		if (operations == null || operations.size() < 2) {
			throw new IllegalStateException("Decision operation must have at least two conditionals (if ... else ...)");
		}
		this.operations = operations;
		for (int i = 0; i < operations.size(); i++) {
			this.operations.get(i).addParent(this);
		}
	}

	@Override
	protected Object resolve(OperationContext context) {
		Boolean condition;
		for (int i = 0; i < operations.size() - 1; i += 2) {
			condition = (Boolean) operations.get(i).evaluate(context);
			if (condition) {
				return operations.get(i + 1).evaluate(context);
			}
		}
		return operations.get(operations.size() - 1).evaluate(context);
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		List<AbstractOperation> clonedOperations = new ArrayList<>();
		for (AbstractOperation op : operations) {
			clonedOperations.add(op.copy(context));
		}
		return new DecisionOperation(clonedOperations);
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public List<AbstractOperation> getOperations() {
		return operations;
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

}
