package com.fearrux.validator;

import java.util.regex.Pattern;

public class PlayerNameValidator implements Validator<String> {
    private static final int MINIMUM_LENGTH = 3;
    private static final int MAXIMUM_LENGTH = 20;
    private static final Pattern NAME_RU_FORMAT = Pattern.compile("^[а-яА-Я\\s.]+$");
    private static final Pattern NAME_EN_FORMAT = Pattern.compile("^[a-zA-z\\s.]+$");

    @Override
    public ValidationResult validate(String object) {
        ValidationResult validationResult = new ValidationResult();
        if (object.isEmpty()) {
            validationResult.add(
                    Error.of("empty.name", "The name cannot be empty.")
            );
        }
        if (object.length() < MINIMUM_LENGTH || object.length() > MAXIMUM_LENGTH) {
            validationResult.add(
                    Error.of("invalid.length", "The name must be between 2 and 20 characters long.")
            );
        }
        if (!NAME_EN_FORMAT.matcher(object).matches() && !NAME_RU_FORMAT.matcher(object).matches()) {
            validationResult.add(
                    Error.of("invalid.name", "The name must consist of the English or Russian alphabet.")
            );
        }
        return validationResult;
    }
}
