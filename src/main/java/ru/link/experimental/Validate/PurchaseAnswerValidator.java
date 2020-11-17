package ru.link.experimental.Validate;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class PurchaseAnswerValidator extends Validator {

    public ValidatorResponse validateAnswerId(UUID id){
        if (isNull(id)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_ANSWER_ID_ERROR);
        }

        if (!isExist(id)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_ANSWER_ID_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public ValidatorResponse validateQuestionId(UUID id){
        if (isNull(id)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_ANSWER_QUESTION_ID_ERROR);
        }

        if (!isExist(id)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_ANSWER_QUESTION_ID_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public ValidatorResponse validateContent(String content){
        if (isEmpty(content)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_ANSWER_CONTENT_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public ValidatorResponse validatePublicity(Boolean publicity){
        if (isNull(publicity)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_ANSWER_PUBLICITY_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

}
