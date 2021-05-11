package utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum  ErrorCode {

    INVALID_DATA_ERROR(900),
    SQL_RUNNING_ERROR(901),
    CREATE_DATA_ERROR(902),
    READ_DATA_ERROR(903),
    THREAD_ERROR(904);

    @Getter
    int code;
}
