package utils.exceptions;

import utils.BaseException;

public class ThreadErrorException extends BaseException {

    public ThreadErrorException(String message, int code) {
        super(message, code);
    }

    public ThreadErrorException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }
}
