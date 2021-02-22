package io.github.mportilho.mathsentenceparser.operation.others;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.other.FunctionOperation;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.DynamicFunction;
import io.github.mportilho.mathsentenceparser.syntaxtree.function.DynamicFunctionContext;

public class TestFunctionOperations {

	@Test
	public void testDynamicFunction() {
		DynamicFunction function = (context, params) -> 5;
		Function<Object[], Object> dynamicFunction = parameters -> function.call(new DynamicFunctionContext(null, null), parameters);
		OperationContext operationContext = new OperationContext();
		operationContext.addExternalFunctions(Collections.singletonMap("teste", dynamicFunction));

		FunctionOperation operation = new FunctionOperation("teste", null, true);
		assertThat(operation.<BigDecimal>evaluate(operationContext)).isEqualByComparingTo("5");
	}

	@Test
	public void testFunctionOperationsWithExternalMethods() {
		throw new IllegalArgumentException();
	}

}
