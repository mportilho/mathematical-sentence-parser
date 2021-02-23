package io.github.mportilho.mathsentenceparser.operation;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Objects;
import java.util.function.Function;

import io.github.mportilho.mathsentenceparser.syntaxtree.visitor.OperationVisitor;

public abstract class AbstractUnaryOperator extends AbstractOperation {

	private AbstractOperation operand;
	private OperatorPosition operatorPosition;

	public AbstractUnaryOperator(AbstractOperation operand, OperatorPosition operatorPosition) {
		this.operand = operand;
		this.operatorPosition = Objects.requireNonNull(operatorPosition, "Operator token position of unary operation is required");
		this.operand.addParent(this);
	}

	public AbstractOperation getOperand() {
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

	@Override
	public void composeTextualRepresentation(StringBuilder builder) {
		switch (operatorPosition) {
		case FUNCTION:
			builder.append(getOperationToken()).append('(');
			getOperand().generateRepresentation(builder);
			builder.append(')');
			break;
		case LEFT:
			builder.append(getOperationToken());
			getOperand().generateRepresentation(builder);
			break;
		case RIGHT:
			getOperand().generateRepresentation(builder);
			builder.append(getOperationToken());
			break;
		case WRAPPED:
			builder.append(getOperationToken());
			getOperand().generateRepresentation(builder);
			builder.append(getOperationToken());
			break;
		default:
			throw new IllegalStateException("Operation position not implemented: " + getOperatorPosition());

		}
	}

	@Override
	public <T> T accept(OperationVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public OperatorPosition getOperatorPosition() {
		return operatorPosition;
	}

	public enum OperatorPosition {
		LEFT, RIGHT, WRAPPED, FUNCTION
	}
}
