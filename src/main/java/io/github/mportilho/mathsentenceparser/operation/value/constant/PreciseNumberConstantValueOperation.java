package io.github.mportilho.mathsentenceparser.operation.value.constant;

import ch.obermuhlner.math.big.BigDecimalMath;
import io.github.mportilho.mathsentenceparser.ParsingContext;

public class PreciseNumberConstantValueOperation extends AbstractConstantValueOperation {

	public PreciseNumberConstantValueOperation(Object value) {
		super(value);
	}

	@Override
	protected Object resolve(ParsingContext context) {
		return BigDecimalMath.toBigDecimal(getValue().toString(), context.getMathContext());
	}

}
