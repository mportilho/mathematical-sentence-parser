package io.github.mportilho.mathsentenceparser.operation.precise.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

import io.github.mportilho.mathsentenceparser.OperationContext;
import io.github.mportilho.mathsentenceparser.operation.AbstractBinaryOperation;
import io.github.mportilho.mathsentenceparser.operation.AbstractOperation;
import io.github.mportilho.mathsentenceparser.operation.CloningContext;

public class PreciseNumberRoundingOperation extends AbstractBinaryOperation {

	private RoundingEnum roundingEnum;

	public PreciseNumberRoundingOperation(AbstractOperation leftOperand, AbstractOperation rightOperand, RoundingEnum roundingEnum) {
		super(leftOperand, rightOperand);
		this.roundingEnum = roundingEnum;
	}

	@Override
	protected Object resolve(OperationContext context) {
		BigDecimal number = getLeftOperand().evaluate(context);
		BigDecimal roundingFactor = getRightOperand().evaluate(context);
		switch (roundingEnum) {
		case CEILING:
			return number.setScale(roundingFactor.intValue(), RoundingMode.CEILING);
		case DOWN:
			return number.setScale(roundingFactor.intValue(), RoundingMode.DOWN);
		case FLOOR:
			return number.setScale(roundingFactor.intValue(), RoundingMode.FLOOR);
		case HALF_DOWN:
			return number.setScale(roundingFactor.intValue(), RoundingMode.HALF_DOWN);
		case HALF_EVEN:
			return number.setScale(roundingFactor.intValue(), RoundingMode.HALF_EVEN);
		case HALF_UP:
			return number.setScale(roundingFactor.intValue(), RoundingMode.HALF_UP);
		case UP:
			return number.setScale(roundingFactor.intValue(), RoundingMode.UP);
		case UNNECESSARY:
			return number.setScale(roundingFactor.intValue(), RoundingMode.UNNECESSARY);
		default:
			throw new IllegalStateException("Rounding method not implemented: " + context);
		}
	}

	@Override
	protected AbstractOperation createClone(CloningContext context) throws Throwable {
		PreciseNumberRoundingOperation operation = (PreciseNumberRoundingOperation) super.copy(context);
		operation.roundingEnum = this.roundingEnum;
		return operation;
	}

	@Override
	protected String getOperationToken() {
		return roundingEnum.toString();
	}

	public enum RoundingEnum {
		UP("up"), DOWN("down"), CEILING("ceiling"), FLOOR("floor"), HALF_UP("halfUp"), HALF_DOWN("halfDown"), HALF_EVEN("halfEven"),
		UNNECESSARY("Unnecessary");

		private String mnemonic;

		private RoundingEnum(String mnemonic) {
			this.mnemonic = mnemonic;
		}

		@Override
		public String toString() {
			return mnemonic;
		}
	}
}
