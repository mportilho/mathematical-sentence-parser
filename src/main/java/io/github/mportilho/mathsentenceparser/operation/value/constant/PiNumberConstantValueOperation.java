package io.github.mportilho.mathsentenceparser.operation.value.constant;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.ParsingContext;

public class PiNumberConstantValueOperation extends AbstractConstantValueOperation {

	public PiNumberConstantValueOperation() {
		this("pi");
	}

	public PiNumberConstantValueOperation(Object value) {
		super(value);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return BigDecimalMath.pi(context.getMathContext());
	}

}
