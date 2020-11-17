package ru.link.experimental.Validate;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class SupplyValidator extends Validator {

    public ValidatorResponse validateId(UUID id){
        if (isNull(id)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_SUPPLY_ID_ERROR);
        }

        if (!isExist(id)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SUPPLY_ID_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public ValidatorResponse validatePurchaseId(UUID purchaseId){
        if (isNull(purchaseId)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_PURCHASE_ID_ERROR);
        }

        if (!isExist(purchaseId)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_PURCHASE_ID_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public ValidatorResponse validateCost(Long cost){
        if (isNull(cost)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_COST_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public ValidatorResponse validateTechnologyStack(String technologyStack){
        if (isEmpty(technologyStack)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_T_STACK_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public ValidatorResponse validateStructure(String structure){
        if (isEmpty(structure)){
            return new ValidatorResponse(ValidatorStatus.ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_STRUCTURE_ERROR);
        }

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public ValidatorResponse validateComment(String comment){

        return new ValidatorResponse(ValidatorStatus.OK, HttpStatus.OK, ValidatorMessages.OK);
    }

}
