package by.epamtc.ivangavrilovich.shop.service.exceptions;

public class InvalidInputsException extends Exception {
    public InvalidInputsException() {
        super();
    }

    public InvalidInputsException(String message) {
        super(message);
    }

    public InvalidInputsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputsException(Throwable cause) {
        super(cause);
    }

    protected InvalidInputsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
