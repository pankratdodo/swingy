package utils.exceptions;

import utils.BaseException;

public class ReadDataErrorException   extends BaseException {

    public ReadDataErrorException(String message, int code) {
        super(message, code);
    }

    public ReadDataErrorException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }
}
