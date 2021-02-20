package io.github.mportilho.mathsentenceparser.operation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.operation.impl.GenericBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericOperationOne;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericOperationTwo;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericUnaryOperation;
import io.github.mportilho.mathsentenceparser.operation.value.constant.PreciseNumberConstantValueOperation;

public class TestCopingOperations {

	@Test
	public void testCopingUnaryOperation() throws Throwable {
		GenericOperationOne genericOperationOne = new GenericOperationOne();
		GenericUnaryOperation operation = new GenericUnaryOperation(genericOperationOne);

		GenericUnaryOperation copyOperation = (GenericUnaryOperation) operation.createClone(new CloningContext());
		GenericOperationOne copyGenericOperationOne = (GenericOperationOne) copyOperation.getOperand();

		GenericUnaryOperation copyOperation2 = (GenericUnaryOperation) copyOperation.createClone(new CloningContext());
		GenericOperationOne copyGenericOperationOne2 = (GenericOperationOne) copyOperation2.getOperand();

		assertThat(operation).isNotEqualTo(copyOperation);
		assertThat(genericOperationOne).isNotEqualTo(copyGenericOperationOne);

		assertThat(copyOperation2).isNotEqualTo(copyOperation);
		assertThat(copyGenericOperationOne2).isNotEqualTo(copyGenericOperationOne);
	}

	@Test
	public void testCopingBinaryOperation() throws Throwable {
		GenericOperationOne genericOperationOne = new GenericOperationOne();
		GenericOperationTwo genericOperationTwo = new GenericOperationTwo();
		GenericBinaryOperation operation = new GenericBinaryOperation(genericOperationOne, genericOperationTwo);

		GenericBinaryOperation copyOperation = (GenericBinaryOperation) operation.createClone(new CloningContext());
		GenericOperationOne copyGenericOperationOne = (GenericOperationOne) copyOperation.getLeftOperand();
		GenericOperationTwo copyGenericOperationTwo = (GenericOperationTwo) copyOperation.getRightOperand();

		GenericBinaryOperation copyOperation2 = (GenericBinaryOperation) operation.createClone(new CloningContext());
		GenericOperationOne copyGenericOperationOne_2 = (GenericOperationOne) copyOperation2.getLeftOperand();
		GenericOperationTwo copyGenericOperationTwo_2 = (GenericOperationTwo) copyOperation2.getRightOperand();

		assertThat(operation).isNotEqualTo(copyOperation);
		assertThat(genericOperationOne).isNotEqualTo(copyGenericOperationOne);
		assertThat(genericOperationTwo).isNotEqualTo(copyGenericOperationTwo);

		assertThat(copyOperation2).isNotEqualTo(copyOperation);
		assertThat(copyGenericOperationOne_2).isNotEqualTo(copyGenericOperationOne);
		assertThat(copyGenericOperationTwo_2).isNotEqualTo(copyGenericOperationTwo);
	}

	@Test
	public void testCopingConstantValueOperation() throws Throwable {
		AbstractOperation operation = new PreciseNumberConstantValueOperation("3");
		PreciseNumberConstantValueOperation copyOperation = (PreciseNumberConstantValueOperation) operation.createClone(new CloningContext());
		assertThat(copyOperation).isNotEqualTo(operation);
		assertThat(copyOperation.getValue()).isEqualTo(((PreciseNumberConstantValueOperation) operation).getValue());
	}

}
