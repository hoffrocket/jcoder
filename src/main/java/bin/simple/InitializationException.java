package bin.simple;

public class InitializationException extends RuntimeException {

	public InitializationException(String message, Throwable source) {
		super(message, source);
	}

	public InitializationException(String message) {
		super(message);
	}
}
