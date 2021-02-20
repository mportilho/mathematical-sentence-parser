package io.github.mportilho.mathsentenceparser.operation.datetime;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.parser.OperationVisitor;

public abstract class AbstractDateTimeOperation extends AbstractOperation {

	private AbstractOperation leftOperand;
	private AbstractOperation rightOperand;
	private DateElementEnum dateElement;

	public AbstractDateTimeOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, DateElementEnum dateElement) {
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.leftOperand.addParent(this);
		this.rightOperand.addParent(this);
		this.dateElement = dateElement;
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		CallSite callSite = cacheCopingFunction(getClass(), clazz -> {
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			MethodType factoryMethodType = MethodType.methodType(DateFunction.class);
			MethodType functionMethodType = MethodType.methodType(void.class, AbstractOperation.class, AbstractOperation.class,
					DateElementEnum.class);
			MethodHandle implementationMethodHandle = lookup.findConstructor(clazz, functionMethodType);
			return LambdaMetafactory.metafactory( //
					lookup, //
					"apply", //
					factoryMethodType, //
					functionMethodType.generic(), //
					implementationMethodHandle, //
					implementationMethodHandle.type());
		});
		return ((DateFunction<AbstractOperation, AbstractOperation, DateElementEnum, AbstractOperation>) callSite.getTarget().invokeExact())
				.apply(leftOperand.copy(context), rightOperand.copy(context), dateElement);
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public final AbstractOperation getLeftOperand() {
		return leftOperand;
	}

	public final AbstractOperation getRightOperand() {
		return rightOperand;
	}

	public final DateElementEnum getDateElement() {
		return dateElement;
	}

	static interface DateFunction<P1, P2, T, R> {
		R apply(P1 p1, P2 p2, T t);
	}

}
