package io.github.mportilho.mathsentenceparser.operation.value.variable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.OperationContext;

public class TestVariableValueOperations {

	private OperationContext context = new OperationContext();

	@Test
	public void testProvidedVariableValueOperations() {
		ProvidedVariableValueOperation operation;

		operation = new ProvidedVariableValueOperation("A");
		operation.provideNewValue(BigDecimal.ZERO);
		assertThat(operation.getVariableName()).isEqualTo("A");
		assertThat(operation.getProvidedValue()).isEqualTo(BigDecimal.ZERO);
	}

	@Test
	public void testVariableValueProvider() {
		ProvidedVariableValueOperation operation;
		VariableValueProvider provider = context -> BigDecimal.ONE.add(BigDecimal.ONE);

		operation = new ProvidedVariableValueOperation("B");
		operation.provideNewValue(provider);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo(BigDecimal.valueOf(2));
	}

	@Test
	public void testNullVariableValue() {
		assertThatThrownBy(() -> new ProvidedVariableValueOperation("a").provideNewValue(null)).isInstanceOf(IllegalArgumentException.class);
	}

}
