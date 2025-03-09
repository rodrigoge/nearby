package com.api.nearby.account_service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorInformation {

    private HttpStatus httpStatus;
    private OffsetDateTime dateTime;
    private ErrorTypeEnum errorTypeEnum;
    private String message;
}
