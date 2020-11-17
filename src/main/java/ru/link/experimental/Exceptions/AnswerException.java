package ru.link.experimental.Exceptions;

import ru.link.experimental.Validate.ValidatorResponse;

public class AnswerException extends Exception {
    public static ValidatorResponse validatorResponse;

    public AnswerException(ValidatorResponse validatorResponse) {
        super(validatorResponse.getMessage());
        this.validatorResponse = validatorResponse;
    }

    public static ValidatorResponse getValidatorResponse() {
        return validatorResponse;
    }
}
