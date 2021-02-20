package io.github.mportilho.mathsentenceparser.operation.others;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.other.AssignedVariableOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.PreciseNumberConstantValueOperation;

public class TestAssignedVariableOperations {

	private OperationContext context = new OperationContext();

	@Test
	public void testAssignedVariableOperation() {
		PreciseNumberConstantValueOperation valueOperation = new PreciseNumberConstantValueOperation("2");
		AssignedVariableOperation operation = new AssignedVariableOperation("AB", valueOperation);

		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");
		assertThat(operation.getVariableName()).isEqualTo("AB");
	}

	@Test
	public void testCopingAssignedVariableOperation() throws Throwable {
		PreciseNumberConstantValueOperation valueOperation = new PreciseNumberConstantValueOperation("2");
		AssignedVariableOperation operation = new AssignedVariableOperation("AB", valueOperation);

		AssignedVariableOperation copyOperation = (AssignedVariableOperation) operation.copy(new CloningContext());

		assertThat(operation).isNotEqualTo(copyOperation);
		assertThat(operation.getAssignedOperation()).isNotEqualTo(copyOperation.getAssignedOperation());
		assertThat(operation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");
		assertThat(copyOperation.<BigDecimal>evaluate(context)).isEqualByComparingTo("2");
		assertThat(operation.getVariableName()).isEqualTo("AB");
		assertThat(copyOperation.getVariableName()).isEqualTo("AB");
	}

}
