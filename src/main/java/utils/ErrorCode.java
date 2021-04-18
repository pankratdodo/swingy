package utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum  ErrorCode {

    INVALID_DATA_ERROR(900),
    SQL_RUNNING_ERROR(901);

    @Getter
    int code;
}
