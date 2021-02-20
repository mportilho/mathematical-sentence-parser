package io.github.mportilho.mathsentenceparser.operation.value.constant;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import io.github.mportilho.mathsentenceparser.OperationContext;

public class TimeConstantValueOperation extends AbstractConstantValueOperation {

	private boolean current;

	public TimeConstantValueOperation(String value) {
		super(value);
	}

	public TimeConstantValueOperation() {
		super("currTime");
		this.current = true;
	}

	@Override
	protected Object resolve(OperationContext context) {
		if (current) {
			return context.getCurrentDateTime().toLocalTime();
		}
		return DateTimeFormatter.ISO_LOCAL_TIME.parse(getValue().toString(), LocalTime::from);
	}

}
