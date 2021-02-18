package io.github.mportilho.mathsentenceparser.operation.value.variable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.github.mportilho.mathsentenceparser.ParsingContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseProductOfSequenceOperation;
import io.github.mportilho.mathsentenceparser.operation.precise.math.PreciseSummationOperation;

public class SequenceVariableValueOperation extends AbstractVariableValueOperation {

	private static final Set<Class<? extends AbstractOperation>> STOP_ON = new HashSet<>(
			Arrays.asList(PreciseSummationOperation.class, PreciseProductOfSequenceOperation.class));

	public SequenceVariableValueOperation(String variableName) {
		super(variableName);
	}

	public void provideNewValue(int newValue) {
		clearCache(STOP_ON);
		super.provideNewValue(newValue);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return getProvidedValue();
	}

}
