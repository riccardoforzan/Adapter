package exceptions;

public class IllegalStateException extends RuntimeException{

    public IllegalStateException() {
        super();
    }

    public IllegalStateException(String message) {
        super(message);
    }
}
