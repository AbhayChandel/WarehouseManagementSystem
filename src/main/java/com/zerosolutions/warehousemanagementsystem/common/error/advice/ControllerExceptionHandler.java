package com.zerosolutions.warehousemanagementsystem.common.error.advice;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResult errorResult = new ErrorResult();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorResult.getFieldValidationErrors()
                    .add(new FieldValidationError(fieldError.getField(),
                            fieldError.getDefaultMessage()));
        }
        return errorResult;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    ErrorResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.getFieldValidationErrors()
                .add(new FieldValidationError(e.getName(), "value is not valid"));
        return errorResult;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    ErrorResult handleConstraintViolationException(ConstraintViolationException e) {
        ErrorResult errorResult = new ErrorResult();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errorResult.getFieldValidationErrors()
                    .add(new FieldValidationError(((PathImpl) violation.getPropertyPath()).getLeafNode().getName(),
                            "value is not valid"));
        }
        return errorResult;
    }
}
