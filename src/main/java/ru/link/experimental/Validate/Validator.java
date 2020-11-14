package ru.link.experimental.Validate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.regex.*;

import static ru.link.experimental.Validate.ValidatorStatus.*;

@Component
public class Validator {
    private static final int MIN_LOGIN_SIZE = 2;
    private static final int MAX_LOGIN_SIZE = 30;

    private static final int MIN_PASSWORD_SIZE = 8;
    private static final int MAX_PASSWORD_SIZE = 20;

    private static final int MAX_FIRST_NAME_SIZE = 20;

    private static final int MAX_LAST_NAME_SIZE = 20;

    private static final int MAX_PATRONYMIC_SIZE = 20;

    private static final int MAX_COMPANY_NAME_SIZE = 30;

    private static final int MAX_COMPANY_ADDRESS_SIZE = 50;

    private static final int MAX_COMPANY_KIND_OF_ACTIVITY_SIZE = 100;

    private static final int MAX_COMPANY_TECHNOLOGY_STACK_SIZE = 100;

    //TIN - taxpayer identification number = индивидуальный номер налогоплательщика (ИНН/УНН)
    private static final int MAX_TIN_SIZE = 12;

    private static final int MIN_ACCOUNT_NUMBER_SIZE = 20;
    private static final int MAX_ACCOUNT_NUMBER_SIZE = 34;

    private static final int MIN_PHONE_NUMBER_SIZE = 11;
    private static final int MAX_PHONE_NUMBER_SIZE = 12;

    private static final int MIN_EMAIL_SIZE = 5;
    private static final int MAX_EMAIL_SIZE = 40;

    public static ValidatorResponse validateLogin(String login){
        if (login.isEmpty()) {
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_LOGIN_FIELD_ERROR);
        }

        if (!(login.length() >= MIN_LOGIN_SIZE && login.length() <= MAX_LOGIN_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_LOGIN_SIZE_ERROR);
        }

        Pattern loginPattern = Pattern.compile("(?:\\D)\\w+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = loginPattern.matcher(login);

        if (!matcher.matches()){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_LOGIN_ERROR);
        }

        /**
         * TODO: Проверка на существование такого же логина
         *
         * return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.LOGIN_ALREADY_EXIST_ERROR);
         */

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validatePassword(String password){
        if (password.isEmpty()){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_PASSWORD_FIELD_ERROR);
        }

        if (!(password.length() >= MIN_PASSWORD_SIZE && password.length() <= MAX_PASSWORD_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_PASSWORD_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validateFirstName(String firstName){
        if (firstName.isEmpty()){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_FIRST_NAME_FIELD_ERROR);
        }

        if (!(firstName.length() <= MAX_FIRST_NAME_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_FIRST_NAME_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validateLastName(String lastName){
        if (lastName.isEmpty()){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_LAST_NAME_FIELD_ERROR);
        }

        if (!(lastName.length() <= MAX_LAST_NAME_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_LAST_NAME_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validatePatronymic(String patronymic){
        if (!(patronymic.length() <= MAX_PATRONYMIC_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_PATRONYMIC_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validateCompanyName(String companyName){
        if (companyName.isEmpty()){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_COMPANY_NAME_FIELD_ERROR);
        }

        if (!(companyName.length() <= MAX_COMPANY_NAME_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_COMPANY_NAME_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validateCompanyDescription(String companyDescription){
        //TODO: Нужна ли валидация описания компании, если длина текста до 1к символов и поле не является обязательным?
        return null;
    }

    public static ValidatorResponse validateCompanyAddress(String companyAddress){
        Pattern addressPattern = Pattern.compile("[а-я0-9 .,]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = addressPattern.matcher(companyAddress);

        if (!matcher.matches()){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_ADDRESS_ERROR);
        }

        if (!(companyAddress.length() <= MAX_COMPANY_ADDRESS_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_COMPANY_ADDRESS_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validateCompanyKindOfActivity(String kindOfActivity){
        if (kindOfActivity.isEmpty()){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_COMPANY_KIND_OF_ACTIVITY_FIELD_ERROR);
        }

        if (!(kindOfActivity.length() <= MAX_COMPANY_KIND_OF_ACTIVITY_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_COMPANY_KIND_OF_ACTIVITY_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validateCompanyTechnologyStack(String technologyStack){
        if (!(technologyStack.length() <= MAX_COMPANY_TECHNOLOGY_STACK_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_COMPANY_TECHNOLOGY_STACK_SIZE_ERROR);
        }

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validateTIN(String tIN){
        if (tIN.isEmpty()) {
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_TIN_FIELD_ERROR);
        }

        if (!(tIN.length() <= MAX_TIN_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_TIN_SIZE_ERROR);
        }

        Pattern loginPattern = Pattern.compile("[A-Z0-9]+");
        Matcher matcher = loginPattern.matcher(tIN);

        if (!matcher.matches()){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_TIN_ERROR);
        }

        /**
         * TODO: Проверка на существование такого же ИНН
         *
         * return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.TIN_ALREADY_EXIST_ERROR);
         */

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validateAccountNumber(String accountNumber){
        if (!(accountNumber.length() >= MIN_ACCOUNT_NUMBER_SIZE && accountNumber.length() <= MAX_ACCOUNT_NUMBER_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_ACCOUNT_NUMBER_SIZE_ERROR);
        }

        Pattern accountNumberPattern = Pattern.compile("[A-Z0-9]+");
        Matcher matcher = accountNumberPattern.matcher(accountNumber);

        if (!matcher.matches()){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_ACCOUNT_NUMBER_ERROR);
        }

        /**
         * TODO: Проверка на существование такого же номера счёта
         *
         * return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.ACCOUNT_NUMBER_ALREADY_EXIST_ERROR);
         */

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validatePhoneNumber(String phoneNumber){
        if (!(phoneNumber.length() >= MIN_PHONE_NUMBER_SIZE && phoneNumber.length() <= MAX_PHONE_NUMBER_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_PHONE_NUMBER_SIZE_ERROR);
        }

        Pattern phoneNumberPattern = Pattern.compile("(?:[+])(?:[0-9]{1,3})\\([0-9]{2,3}\\)[0-9]+\\-[0-9]+\\-[0-9]+");
        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);

        if (!matcher.matches()){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_PHONE_NUMBER_ERROR);
        }

        /**
         * TODO: Проверка на существование такого же номера телефона
         *
         * return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.PHONE_NUMBER_ALREADY_EXIST_ERROR);
         */

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }

    public static ValidatorResponse validateEmail(String email){
        if (email.isEmpty()) {
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMPTY_EMAIL_FIELD_ERROR);
        }

        if (!(email.length() >= MIN_EMAIL_SIZE && email.length() <= MAX_EMAIL_SIZE)){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_EMAIL_SIZE_ERROR);
        }

        Pattern emailPattern = Pattern.compile("[a-z0-9._\\-]{10,15}\\@[a-z0-9._\\-]{10,15}\\.[a-z0-9._\\-]{2,7}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);

        if (!matcher.matches()){
            return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.WRONG_SYMBOLS_IN_EMAIL_ERROR);
        }

        /**
         * TODO: Проверка на существование такого же e-mail
         *
         * return new ValidatorResponse(ERROR, HttpStatus.BAD_REQUEST, ValidatorMessages.EMAIL_ALREADY_EXIST_ERROR);
         */

        return new ValidatorResponse(OK, HttpStatus.OK, ValidatorMessages.OK);
    }
}

