package ru.link.experimental.Validate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PurchaseQuestionValidator extends Validator {

    public ValidatorResponse validateQuestionId(UUID id){
        if (isNull(id)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_QUESTION_ID_ERROR);
        }

        if (!isExist(id)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_QUESTION_ID_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public ValidatorResponse validatePurchaseId(UUID id){
        if (isNull(id)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_QUESTION_PURCHASE_ID_ERROR);
        }

        if (!isExist(id)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_QUESTION_PURCHASE_ID_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public ValidatorResponse validateName(String name){
        if (isEmpty(name)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_QUESTION_NAME_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public ValidatorResponse validateContent(String content){
        if (isEmpty(content)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_QUESTION_CONTENT_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

}
