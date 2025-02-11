package com.fearrux.exception;

public class InvalidPlayerIdException extends RuntimeException {
    public InvalidPlayerIdException(String message) {
        super(message);
    }
}
