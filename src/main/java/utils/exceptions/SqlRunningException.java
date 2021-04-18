package utils.exceptions;

import utils.BaseException;

public class SqlRunningException extends BaseException {

    public SqlRunningException(String message, int code) {
        super(message, code);
    }

    public SqlRunningException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }
}
