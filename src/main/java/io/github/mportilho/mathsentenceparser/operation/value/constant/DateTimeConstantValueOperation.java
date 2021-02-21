package io.github.mportilho.mathsentenceparser.operation.value.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;
import io.github.mportilho.mathsentenceparser.operation.OperationContext;

public class DateTimeConstantValueOperation extends AbstractConstantValueOperation {

	private boolean current;

	public DateTimeConstantValueOperation(String value) {
		super(value);
	}

	public DateTimeConstantValueOperation() {
		super("currDateTime");
		this.current = true;
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		DateTimeConstantValueOperation operation = (DateTimeConstantValueOperation) super.createClone(context);
		operation.current = this.current;
		return operation;
	}

	@Override
	protected Object resolve(OperationContext context) {
		if (current) {
			return context.getCurrentDateTime();
		}
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(getValue().toString(), LocalDateTime::from);
	}

}
