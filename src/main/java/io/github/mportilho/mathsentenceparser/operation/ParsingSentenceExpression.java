package io.github.mportilho.mathsentenceparser.operation;

public class ParsingSentenceExpression extends RuntimeException {

	private static final long serialVersionUID = 682697189982483235L;

	public ParsingSentenceExpression() {
		super();
	}

	public ParsingSentenceExpression(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParsingSentenceExpression(String message, Throwable cause) {
		super(message, cause);
	}

	public ParsingSentenceExpression(String message) {
		super(message);
	}

	public ParsingSentenceExpression(Throwable cause) {
		super(cause);
	}

}
