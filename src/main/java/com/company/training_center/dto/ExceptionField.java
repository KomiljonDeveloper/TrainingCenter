package com.company.training_center.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionField {
    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> methodValidException(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(ResponseDto.<Void>builder()
                        .code(-4)
                        .message("Validation error!")
                        .errors(e.getFieldErrors().stream().map(fieldError -> {
                            String rejectedValue = (String) fieldError.getRejectedValue();
                            String field = fieldError.getField();
                            String defaultMessage = fieldError.getDefaultMessage();
                            return new ErrorDto(field, String.format("Message : " + defaultMessage + ", Rejected : %s", rejectedValue));
                        }).toList())
                .build());

    }

}
