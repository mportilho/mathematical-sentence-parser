package io.github.mportilho.mathsentenceparser.operation;

public abstract class AbstractValueOperation extends AbstractOperation {

	private Object value;

	public AbstractValueOperation(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}
}
