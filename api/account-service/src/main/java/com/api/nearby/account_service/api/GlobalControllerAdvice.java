package com.api.nearby.account_service.api;

import com.api.nearby.account_service.exceptions.CustomExceptionHandler;
import com.api.nearby.account_service.exceptions.ErrorInformation;
import com.api.nearby.account_service.exceptions.ErrorTypeEnum;
import com.api.nearby.account_service.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(CustomExceptionHandler.class)
    public ResponseEntity<ErrorInformation> handleCustomExceptionHandler(CustomExceptionHandler exception) {
        var httpStatus = exception.getHttpStatus();
        var errorType = exception.getErrorTypeEnum();
        var message = exception.getMessage();
        return ResponseEntity
                .status(httpStatus)
                .body(ErrorInformation
                        .builder()
                        .httpStatus(httpStatus)
                        .dateTime(Utils.formatDateToResponse(OffsetDateTime.now()))
                        .errorTypeEnum(errorType)
                        .message(message)
                        .build()
                );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorInformation> handleBadCredentialsException(BadCredentialsException exception) {
        var message = exception.getMessage();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ErrorInformation
                        .builder()
                        .httpStatus(HttpStatus.FORBIDDEN)
                        .dateTime(Utils.formatDateToResponse(OffsetDateTime.now()))
                        .errorTypeEnum(ErrorTypeEnum.INVALID_REQUEST)
                        .message(message)
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInformation> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(errorMessage ->
                        errorMessage.getDefaultMessage() != null ?
                                errorMessage.getDefaultMessage() : "Invalid request"
                )
                .findFirst()
                .orElse("Invalid request");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorInformation
                        .builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .dateTime(Utils.formatDateToResponse(OffsetDateTime.now()))
                        .errorTypeEnum(ErrorTypeEnum.INVALID_REQUEST)
                        .message(message)
                        .build()
                );
    }
}
