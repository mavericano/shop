package by.epamtc.ivangavrilovich.shop.service.exceptions;

public class AlreadyRegisteredException extends Exception {
    public AlreadyRegisteredException() {
        super();
    }

    public AlreadyRegisteredException(String message) {
        super(message);
    }

    public AlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyRegisteredException(Throwable cause) {
        super(cause);
    }

    protected AlreadyRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
