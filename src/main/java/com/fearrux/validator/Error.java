package com.fearrux.validator;

import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
    String code;
    String message;

    @Override
    public String toString() {
        return "Error: " + message;
    }
}
