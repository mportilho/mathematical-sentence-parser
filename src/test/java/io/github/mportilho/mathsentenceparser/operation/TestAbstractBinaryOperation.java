package io.github.mportilho.mathsentenceparser.operation;

import java.lang.invoke.CallSite;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.operation.impl.GenericOperationOne;
import io.github.mportilho.mathsentenceparser.operation.impl.GenericOperationTwo;

public class TestAbstractBinaryOperation {

	@Test
	public void testCloner() throws Throwable {
		GenericOperationOne genericOperationOne = new GenericOperationOne();
		GenericOperationTwo genericOperationTwo = new GenericOperationTwo();
		
		AbstractBinaryOperationImpl operation = new AbstractBinaryOperationImpl(genericOperationOne, genericOperationTwo);
		CallSite callSite = operation.createCloningMethodHandle();
		callSite.getTarget().invoke(null);
	}

}
