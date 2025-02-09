package com.fearrux.validator;

public interface Validator <O> {
    ValidationResult validate(O object);
}
