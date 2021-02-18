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
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		CallSite callSite = cacheCopingFunction(getClass(), clazz -> {
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			MethodType factoryMethodType = MethodType.methodType(BiFunction.class);
			MethodType functionMethodType = MethodType.methodType(void.class, AbstractOperation.class, AbstractOperation.class);
			MethodHandle implementationMethodHandle = lookup.findConstructor(clazz, functionMethodType);
			return LambdaMetafactory.metafactory( //
					lookup, //
					"apply", //
					factoryMethodType, //
					functionMethodType.generic(), //
					implementationMethodHandle, //
					implementationMethodHandle.type());
		});
		return ((BiFunction<AbstractOperation, AbstractOperation, AbstractOperation>) callSite.getTarget().invokeExact())
				.apply(leftOperand.copy(context), rightOperand.copy(context));
	}

	protected AbstractOperation getLeftOperand() {
		return leftOperand;
	}

	protected AbstractOperation getRightOperand() {
		return rightOperand;
	}

}
