package com.api.nearby.account_service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ErrorInformation {

    private HttpStatus httpStatus;
    private String dateTime;
    private ErrorTypeEnum errorTypeEnum;
    private String message;
}
