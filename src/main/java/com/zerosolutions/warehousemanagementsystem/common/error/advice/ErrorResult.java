package com.zerosolutions.warehousemanagementsystem.common.error.advice;

import java.util.ArrayList;
import java.util.List;

public class ErrorResult {

    private final List<FieldValidationError> fieldValidationErrors = new ArrayList<>();

    public ErrorResult(String field, String message) {
        this.fieldValidationErrors.add(new FieldValidationError(field, message));
    }

    ErrorResult() {
    }

    public List<FieldValidationError> getFieldValidationErrors() {
        return fieldValidationErrors;
    }
}
