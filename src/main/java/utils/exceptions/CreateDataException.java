package utils.exceptions;

import utils.BaseException;

public class CreateDataException   extends BaseException {

    public CreateDataException(String message, int code) {
        super(message, code);
    }

    public CreateDataException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }
}
