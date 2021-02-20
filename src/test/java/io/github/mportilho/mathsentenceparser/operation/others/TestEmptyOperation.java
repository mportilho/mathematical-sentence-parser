package io.github.mportilho.mathsentenceparser.operation.others;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.other.EmptyOperation;
import io.github.mportilho.mathsentenceparser.operation.value.OperationValueType;
import io.github.mportilho.mathsentenceparser.operation.value.constant.PreciseNumberConstantValueOperation;

public class TestEmptyOperation {

	@Test
	public void testEmptyOperation() {
		EmptyOperation operation = new EmptyOperation(OperationValueType.BOOLEAN, null);
		assertThat(operation.<Boolean>evaluate(null)).isFalse();
	}

	@Test
	public void testEmptyOperationWithOperationMap() {
		Map<String, AbstractOperation> map = new HashMap<>();
		map.put("one", new PreciseNumberConstantValueOperation("1"));
		map.put("two", new PreciseNumberConstantValueOperation("2"));

		EmptyOperation operation = new EmptyOperation(OperationValueType.NUMBER, map);
		assertThat(operation.<BigDecimal>evaluate(null)).isEqualByComparingTo("0");
	}

	@Test
	public void testCopingEmptyOperation() throws Throwable {
		EmptyOperation operation = new EmptyOperation(OperationValueType.BOOLEAN, null);
		EmptyOperation copyOperation = (EmptyOperation) operation.copy(new CloningContext());

		assertThat(operation).isNotEqualTo(copyOperation);
		assertThat(operation.<Boolean>evaluate(null)).isFalse();
		assertThat(copyOperation.<Boolean>evaluate(null)).isFalse();
	}

	@Test
	public void testCopingEmptyOperationWithOperationMap() throws Throwable {
		Map<String, AbstractOperation> map = new HashMap<>();
		map.put("one", new PreciseNumberConstantValueOperation("1"));
		map.put("two", new PreciseNumberConstantValueOperation("2"));

		EmptyOperation operation = new EmptyOperation(OperationValueType.NUMBER, map);
		EmptyOperation copyOperation = (EmptyOperation) operation.copy(new CloningContext());

		assertThat(operation).isNotEqualTo(copyOperation);
		assertThat(operation.<BigDecimal>evaluate(null)).isEqualByComparingTo("0");
		assertThat(copyOperation.<BigDecimal>evaluate(null)).isEqualByComparingTo("0");

		for (String key : operation.getAssignedVariables().keySet()) {
			assertThat(operation.getAssignedVariables().get(key)).isNotEqualTo(copyOperation.getAssignedVariables().get(key));
		}
	}

}
