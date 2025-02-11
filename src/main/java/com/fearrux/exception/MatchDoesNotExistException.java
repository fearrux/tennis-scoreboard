package com.fearrux.exception;

public class MatchDoesNotExistException extends RuntimeException {
    public MatchDoesNotExistException(String message) {
        super(message);
    }
}
