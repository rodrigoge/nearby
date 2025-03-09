package com.api.nearby.account_service.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Getter
public class CustomExceptionHandler extends RuntimeException {

    private final HttpStatus httpStatus;
    private final OffsetDateTime dateTime;
    private final ErrorTypeEnum errorTypeEnum;
    private final String message;

    public CustomExceptionHandler(
            HttpStatus httpStatus,
            OffsetDateTime dateTime,
            ErrorTypeEnum errorTypeEnum,
            String message
    ) {
        super(message);
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
        this.errorTypeEnum = errorTypeEnum;
        this.message = message;
    }
}
