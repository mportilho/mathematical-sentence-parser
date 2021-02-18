package io.github.mportilho.mathsentenceparser.operation;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Function;

public abstract class AbstractUnaryOperator extends AbstractOperation {

	private AbstractOperation operand;

	public AbstractUnaryOperator(AbstractOperation operand) {
		this.operand = operand;
	}

	protected AbstractOperation getOperand() {
		return operand;
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		CallSite callSite = cacheCopingFunction(getClass(), clazz -> {
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			MethodType factoryMethodType = MethodType.methodType(Function.class);
			MethodType functionMethodType = MethodType.methodType(void.class, AbstractOperation.class);
			MethodHandle implementationMethodHandle = lookup.findConstructor(clazz, functionMethodType);
			return LambdaMetafactory.metafactory( //
					lookup, //
					"apply", //
					factoryMethodType, //
					functionMethodType.generic(), //
					implementationMethodHandle, //
					implementationMethodHandle.type());
		});
		return ((Function<AbstractOperation, AbstractOperation>) callSite.getTarget().invokeExact()).apply(operand.copy(context));
	}
}
