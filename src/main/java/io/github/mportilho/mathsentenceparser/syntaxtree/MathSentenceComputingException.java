package io.github.mportilho.mathsentenceparser.syntaxtree;

public class MathSentenceComputingException extends RuntimeException {

	private static final long serialVersionUID = 682697189982483235L;

	public MathSentenceComputingException() {
		super();
	}

	public MathSentenceComputingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MathSentenceComputingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MathSentenceComputingException(String message) {
		super(message);
	}

	public MathSentenceComputingException(Throwable cause) {
		super(cause);
	}

}
