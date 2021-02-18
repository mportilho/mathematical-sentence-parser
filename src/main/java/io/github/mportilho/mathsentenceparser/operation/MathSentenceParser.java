package io.github.mportilho.mathsentenceparser.operation;

public class MathSentenceParser extends RuntimeException {

	private static final long serialVersionUID = 682697189982483235L;

	public MathSentenceParser() {
		super();
	}

	public MathSentenceParser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MathSentenceParser(String message, Throwable cause) {
		super(message, cause);
	}

	public MathSentenceParser(String message) {
		super(message);
	}

	public MathSentenceParser(Throwable cause) {
		super(cause);
	}

}
