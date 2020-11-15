package ru.link.experimental.Validate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.link.experimental.Entities.UserEntity;

import java.util.regex.*;

import static ru.link.experimental.Validate.ValidatorStatus.*;

@Component
public class UserValidator extends Validator{

    private final int MIN_LOGIN_SIZE = 2;

    private final int MAX_LOGIN_SIZE = 30;

    private final int MIN_PASSWORD_SIZE = 8;

    private final int MAX_PASSWORD_SIZE = 20;

    private final int MAX_FIRST_NAME_SIZE = 20;

    private final int MAX_LAST_NAME_SIZE = 20;

    private final int MAX_PATRONYMIC_SIZE = 20;

    private final int MAX_COMPANY_NAME_SIZE = 30;

    private final int MAX_COMPANY_DESCRIPTION_SIZE = 1000;

    private final int MAX_COMPANY_ADDRESS_SIZE = 50;

    private final int MAX_COMPANY_KIND_OF_ACTIVITY_SIZE = 100;

    private final int MAX_COMPANY_TECHNOLOGY_STACK_SIZE = 100;

    private final int MIN_TIN_SIZE = 9; //TIN - taxpayer identification number = индивидуальный номер налогоплательщика (ИНН/УНН)

    private final int MAX_TIN_SIZE = 12;

    private final int MIN_ACCOUNT_NUMBER_SIZE = 20;

    private final int MAX_ACCOUNT_NUMBER_SIZE = 34;

    private final int MIN_PHONE_NUMBER_SIZE = 6;

    private final int MAX_PHONE_NUMBER_SIZE = 12;

    private final int MIN_EMAIL_SIZE = 5;

    private final int MAX_EMAIL_SIZE = 40;

    public ValidatorResponse validateUser(UserEntity userEntity){
        ValidatorResponse validatorResponse;

        if ((validatorResponse = validateLogin(userEntity.getUsername())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validatePassword(userEntity.getPassword())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validateFirstName(userEntity.getFirstName())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validateLastName(userEntity.getLastName())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validatePatronymic(userEntity.getPatronymic())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validateCompanyName(userEntity.getFirmName())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validateCompanyDescription(userEntity.getDescription())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validateCompanyAddress(userEntity.getAddress())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validateCompanyKindOfActivity(userEntity.getActivity())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validateCompanyTechnologyStack(userEntity.getTechnology())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validateTIN(userEntity.getInn())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validateAccountNumber(userEntity.getAccount())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validatePhoneNumber(userEntity.getTelephone())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        if ((validatorResponse = validateEmail(userEntity.getEmail())).getValidatorStatus() == ERROR) {
            return validatorResponse;
        }

        return validatorResponse;
    }

    private ValidatorResponse validateLogin(String login){
        if (isEmpty(login)) {
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_LOGIN_FIELD_ERROR);
        }

        if (!inBounds(login, MIN_LOGIN_SIZE, MAX_LOGIN_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_LOGIN_SIZE_ERROR);
        }

        if (!isMatch(login, "(?:\\D)\\w+", Pattern.CASE_INSENSITIVE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_LOGIN_ERROR);
        }

        if (isExist(login)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.LOGIN_ALREADY_EXIST_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validatePassword(String password){
        if (isEmpty(password)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_PASSWORD_FIELD_ERROR);
        }

        if (!inBounds(password, MIN_PASSWORD_SIZE, MAX_PASSWORD_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_PASSWORD_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validateFirstName(String firstName){
        if (isEmpty(firstName)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_FIRST_NAME_FIELD_ERROR);
        }

        if (!inBounds(firstName, MAX_FIRST_NAME_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_FIRST_NAME_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validateLastName(String lastName){
        if (isEmpty(lastName)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_LAST_NAME_FIELD_ERROR);
        }

        if (!inBounds(lastName, MAX_LAST_NAME_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_LAST_NAME_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validatePatronymic(String patronymic){
        if (!inBounds(patronymic, MAX_PATRONYMIC_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_PATRONYMIC_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validateCompanyName(String companyName){
        if (isEmpty(companyName)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_COMPANY_NAME_FIELD_ERROR);
        }

        if (!inBounds(companyName, MAX_COMPANY_NAME_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_COMPANY_NAME_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validateCompanyDescription(String companyDescription){
        //TODO: Нужна ли валидация описания компании, если длина текста до 1к любых символов и поле не является обязательным?
        if (!inBounds(companyDescription, MAX_COMPANY_DESCRIPTION_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_COMPANY_DESCRIPTION_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validateCompanyAddress(String companyAddress){
        if (!inBounds(companyAddress, MAX_COMPANY_ADDRESS_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_COMPANY_ADDRESS_SIZE_ERROR);
        }

        if (!isMatch(companyAddress, "[а-я0-9 .,]+", Pattern.CASE_INSENSITIVE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_ADDRESS_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validateCompanyKindOfActivity(String kindOfActivity){
        if (isEmpty(kindOfActivity)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_COMPANY_KIND_OF_ACTIVITY_FIELD_ERROR);
        }

        if (!inBounds(kindOfActivity, MAX_COMPANY_KIND_OF_ACTIVITY_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_COMPANY_KIND_OF_ACTIVITY_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validateCompanyTechnologyStack(String technologyStack){
        if (!inBounds(technologyStack, MAX_COMPANY_TECHNOLOGY_STACK_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_COMPANY_TECHNOLOGY_STACK_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validateTIN(String tIN){
        if (isEmpty(tIN)) {
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_TIN_FIELD_ERROR);
        }

        if (!inBounds(tIN, MIN_TIN_SIZE, MAX_TIN_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_TIN_SIZE_ERROR);
        }

        if (!isMatch(tIN, "[A-Z0-9]+")){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_TIN_ERROR);
        }

        if (isExist(tIN)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.TIN_ALREADY_EXIST_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validateAccountNumber(String accountNumber){
        if (!inBounds(accountNumber, MIN_ACCOUNT_NUMBER_SIZE, MAX_ACCOUNT_NUMBER_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_ACCOUNT_NUMBER_SIZE_ERROR);
        }

        if (!isMatch(accountNumber, "[A-Z0-9]+")){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_ACCOUNT_NUMBER_ERROR);
        }

        if (isExist(accountNumber)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.ACCOUNT_NUMBER_ALREADY_EXIST_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validatePhoneNumber(String phoneNumber){
        if (!inBounds(phoneNumber, MIN_PHONE_NUMBER_SIZE, MAX_PHONE_NUMBER_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_PHONE_NUMBER_SIZE_ERROR);
        }

        if (!isMatch(phoneNumber, "(?:[+])(?:[0-9]{1,3})\\([0-9]{2,3}\\)[0-9]+\\-[0-9]+\\-[0-9]+")){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_PHONE_NUMBER_ERROR);
        }

        if (isExist(phoneNumber)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.PHONE_NUMBER_ALREADY_EXIST_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    private ValidatorResponse validateEmail(String email){
        if (isEmpty(email)) {
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_EMAIL_FIELD_ERROR);
        }

        if (!inBounds(email, MIN_EMAIL_SIZE, MAX_EMAIL_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_EMAIL_SIZE_ERROR);
        }

        if (!isMatch(email, "[a-z0-9._\\-]{10,15}\\@[a-z0-9._\\-]{10,15}\\.[a-z0-9._\\-]{2,7}", Pattern.CASE_INSENSITIVE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_EMAIL_ERROR);
        }

        if (isExist(email)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMAIL_ALREADY_EXIST_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }
}

