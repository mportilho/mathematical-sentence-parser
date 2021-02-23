package io.github.mportilho.mathsentenceparser.operation.other;

import java.util.ArrayList;
import java.util.List;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public class DecisionOperation extends AbstractOperation {

	private List<AbstractOperation> operations;

	public DecisionOperation(List<AbstractOperation> operations) {
		if (operations == null || operations.size() < 2) {
			throw new IllegalStateException("Decision operation must have at least two conditionals (if ... then ... else ... endif)");
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
	protected void composeTextualRepresentation(StringBuilder builder) {
		int count = operations.size();
		for (int i = 0; i < count - 1; i += 2) {
			builder = i == 0 ? builder.append("if ") : builder.append(" elsif ");
			builder.append(operations.get(i)).append(" then ").append(operations.get(i + 1));
		}
		builder.append(" else ").append(operations.get(operations.size() - 1)).append(" endif");
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
