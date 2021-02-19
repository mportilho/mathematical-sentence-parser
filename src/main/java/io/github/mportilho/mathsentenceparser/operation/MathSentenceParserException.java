package io.github.mportilho.mathsentenceparser.operation;

public class MathSentenceParserException extends RuntimeException {

	private static final long serialVersionUID = 682697189982483235L;

	public MathSentenceParserException() {
		super();
	}

	public MathSentenceParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MathSentenceParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public MathSentenceParserException(String message) {
		super(message);
	}

	public MathSentenceParserException(Throwable cause) {
		super(cause);
	}

}
