package io.github.mportilho.mathsentenceparser.operation.value.constant;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Function;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public abstract class AbstractConstantValueOperation extends AbstractOperation {

	private String value;

	public AbstractConstantValueOperation(String value) {
		this.value = value;
		cachingForever();
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		CallSite callSite = cacheCopingFunction(getClass(), clazz -> {
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			MethodType factoryMethodType = MethodType.methodType(Function.class);
			MethodType functionMethodType = MethodType.methodType(void.class, String.class);
			MethodHandle implementationMethodHandle = lookup.findConstructor(clazz, functionMethodType);
			return LambdaMetafactory.metafactory( //
					lookup, //
					"apply", //
					factoryMethodType, //
					functionMethodType.generic(), //
					implementationMethodHandle, //
					implementationMethodHandle.type());
		});
		return ((Function<Object, AbstractOperation>) callSite.getTarget().invokeExact()).apply(value);
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public String getValue() {
		return value;
	}

	protected void setValue(String value) {
		this.value = value;
	}

	@Override
	protected String getOperationToken() {
		return "";
	}

}
