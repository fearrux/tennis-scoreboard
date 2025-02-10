package com.fearrux.exception;

import com.fearrux.validator.Error;

import java.util.List;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException(List<Error> errors) {
        super(String.valueOf(errors));
    }
}