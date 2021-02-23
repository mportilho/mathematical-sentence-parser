package io.github.mportilho.mathsentenceparser.operation;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.operation.BaseOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.value.OperationValueType;
import io.github.mportilho.mathsentenceparser.operation.value.constant.PreciseNumberConstantValueOperation;

public class TestBaseOperation {

	private OperationContext context = new OperationContext();

	@Test
	public void testBaseOperation() {
		BaseOperation operation = new BaseOperation(OperationValueType.BOOLEAN, null, null);
		assertThat(operation.<Boolean>evaluate(context)).isFalse();
	}

	@Test
	public void testBaseOperationWithOperationMap() {
		Map<String, AssignedVariableOperation> map = new HashMap<>();
		map.put("one", new AssignedVariableOperation("one", new PreciseNumberConstantValueOperation("1")));
		map.put("two", new AssignedVariableOperation("two", new PreciseNumberConstantValueOperation("2")));

		BaseOperation operation = new BaseOperation(OperationValueType.NUMBER, null, map);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");
	}

	@Test
	public void testCopingBaseOperation() throws Throwable {
		BaseOperation operation = new BaseOperation(OperationValueType.BOOLEAN, null, null);
		BaseOperation copyOperation = (BaseOperation) operation.copy(new CloningContext());

		assertThat(operation).isNotEqualTo(copyOperation);
		assertThat(operation.<Boolean>evaluate(context)).isFalse();
		assertThat(copyOperation.<Boolean>evaluate(context)).isFalse();
	}

	@Test
	public void testCopingBaseOperationWithOperationMap() throws Throwable {
		Map<String, AssignedVariableOperation> map = new HashMap<>();
		map.put("one", new AssignedVariableOperation("one", new PreciseNumberConstantValueOperation("1")));
		map.put("two", new AssignedVariableOperation("two", new PreciseNumberConstantValueOperation("2")));

		BaseOperation operation = new BaseOperation(OperationValueType.NUMBER, null, map);
		BaseOperation copyOperation = (BaseOperation) operation.copy(new CloningContext());

		assertThat(operation).isNotEqualTo(copyOperation);
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");
		assertThat(copyOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("0");

		for (String key : operation.getAssignedVariables().keySet()) {
			assertThat(operation.getAssignedVariables().get(key)).isNotEqualTo(copyOperation.getAssignedVariables().get(key));
		}
	}

}
