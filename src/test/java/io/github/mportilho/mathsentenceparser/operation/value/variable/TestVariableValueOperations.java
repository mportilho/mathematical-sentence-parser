package io.github.mportilho.mathsentenceparser.operation.value.variable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class TestVariableValueOperations {

	private OperationContext context = new OperationContext();

	@Test
	public void testProvidedVariableValueOperations() {
		VariableValueOperation operation;

		operation = new VariableValueOperation("A");
		operation.setValue(BigDecimal.ZERO);
		assertThat(operation.getVariableName()).isEqualTo("A");
		assertThat(operation.getValue()).isEqualTo(BigDecimal.ZERO);
	}

	@Test
	public void testVariableValueProvider() {
		VariableValueOperation operation;
		VariableValueProvider provider = context -> BigDecimal.ONE.add(BigDecimal.ONE);

		operation = new VariableValueOperation("B");
		operation.setValue(provider);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo(BigDecimal.valueOf(2));
	}

	@Test
	public void testNullVariableValue() {
		assertThatThrownBy(() -> new VariableValueOperation("a").setValue(null)).isInstanceOf(IllegalArgumentException.class);
	}

}
