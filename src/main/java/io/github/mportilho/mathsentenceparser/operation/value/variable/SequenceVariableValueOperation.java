package io.github.mportilho.mathsentenceparser.operation.value.variable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseProductOfSequenceOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseSummationOperation;

public class SequenceVariableValueOperation extends AbstractVariableValueOperation {

	private static final Set<Class<? extends AbstractOperation>> STOP_ON = new HashSet<>(
			Arrays.asList(PreciseSummationOperation.class, PreciseProductOfSequenceOperation.class));

	public SequenceVariableValueOperation(String variableName) {
		super(variableName);
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		SequenceVariableValueOperation operation = new SequenceVariableValueOperation(getVariableName());
		operation.value = this.value;
		return operation;
	}

	public void setSequenceIndex(BigDecimal newValue) {
		super.setValue(newValue, STOP_ON);
	}

	@Override
	protected Object resolve(OperationContext context) {
		return getValue();
	}

	@Override
	protected void composeTextualRepresentation(StringBuilder builder) {
		builder.append(getVariableName());
	}

}
