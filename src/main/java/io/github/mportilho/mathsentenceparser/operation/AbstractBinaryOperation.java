package io.github.mportilho.mathsentenceparser.operation;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.BiFunction;

public abstract class AbstractBinaryOperation extends AbstractOperation {

	private AbstractOperation leftOperand;
	private AbstractOperation rightOperand;

	public AbstractBinaryOperation(AbstractOperation leftOperand, AbstractOperation rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	@Override
	protected CallSite createCloningMethodHandle() throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodType factoryMethodType = MethodType.methodType(BiFunction.class);
		MethodType functionMethodType = MethodType.methodType(void.class, new Class[] { AbstractOperation.class, AbstractOperation.class });
		MethodHandle implementationMethodHandle = lookup.findConstructor(getClass(), functionMethodType);
		return LambdaMetafactory.metafactory( //
				lookup, //
				"apply", //
				factoryMethodType, //
				functionMethodType, //
				implementationMethodHandle, //
				implementationMethodHandle.type().dropParameterTypes(0, 1));
	}

	protected AbstractOperation getLeftOperand() {
		return leftOperand;
	}

	protected AbstractOperation getRightOperand() {
		return rightOperand;
	}

}
