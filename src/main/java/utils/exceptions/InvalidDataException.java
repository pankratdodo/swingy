package utils.exceptions;

import utils.BaseException;

public class InvalidDataException  extends BaseException {

    public InvalidDataException(String message, int code) {
        super(message, code);
    }

    public InvalidDataException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }
}
