package com.example.javaEcommerce.exceptions;

import com.example.javaEcommerce.common.Constant;
import com.example.javaEcommerce.dto.ResponseDto;
import com.example.javaEcommerce.enums.MessageEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handleException(Exception e){
        MessageEnum messageEnum = MessageEnum.ERR_APPLICATION;
        String message = Objects.requireNonNullElse(e.getMessage(), messageEnum.getMessage());
        ResponseDto<?> responseError = this.buildResponseErr(message, messageEnum.getCode());
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseDto<?> buildResponseErr(String message, String code) {
        ResponseDto<?> responseError = new ResponseDto<>();
        responseError.setStatus(Constant.ERROR);
        responseError.setMessage(message);
        responseError.setCode(Objects.requireNonNullElse(code, Constant.ERROR));

        return responseError;
    }

}
