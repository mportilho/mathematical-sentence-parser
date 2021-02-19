package io.github.mportilho.mathsentenceparser.operation.other;

import static java.util.Objects.nonNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.value.OperationValueType;
import io.github.mportilho.mathsentenceparser.parser.OperationVisitor;

public class EmptyOperation extends AbstractOperation {

	private OperationValueType type;
	private Map<String, AbstractOperation> assignedVariables;

	public EmptyOperation(OperationValueType type, Map<String, AbstractOperation> assignedVariables) {
		this.type = type;
		this.assignedVariables = assignedVariables;
	}

	@Override
	protected Object resolve(OperationContext context) {
		if (assignedVariables != null) {
			try {
				for (AbstractOperation operation : assignedVariables.values()) {
					operation.evaluate(context);
				}
			} catch (Exception e) {
			}
		}
		switch (type) {
		case BOOLEAN:
			return Boolean.FALSE;
		case NUMBER:
			return BigDecimal.ZERO;
		default:
			throw new IllegalStateException(String.format("Type '%s' not valid for an empty operation. Only Boolean or Number allowed", type));
		}
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		Map<String, AbstractOperation> newAssignedVariables = null;
		if (nonNull(assignedVariables)) {
			newAssignedVariables = new HashMap<>();
			for (Entry<String, AbstractOperation> entry : assignedVariables.entrySet()) {
				newAssignedVariables.put(entry.getKey(), entry.getValue().copy(context));
			}
		}
		return new EmptyOperation(type, newAssignedVariables);
	}
	
	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public Map<String, AbstractOperation> getAssignedVariables() {
		return assignedVariables;
	}

	@Override
	protected String getOperationToken() {
		return "<empty>";
	}

}
