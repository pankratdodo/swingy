package utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {

        /**
         * Код исключения
         */
        @Getter
        int code;

    public BaseException(String message, int code) {
            super(message);
            this.code = code;
        }

    public BaseException(String message, Throwable cause, int code) {
            super(message, cause);
            this.code = code;
        }
}
