package com.fearrux.exception;

import com.fearrux.validator.Error;

import java.util.List;
import java.util.stream.Collectors;

public class InvalidNameException extends RuntimeException {

    public InvalidNameException(List<Error> errors) {
        super(errors.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    }
}