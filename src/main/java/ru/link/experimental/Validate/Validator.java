package ru.link.experimental.Validate;

import java.util.regex.*;

public abstract class Validator {
    public boolean isEmpty(String field){
        return field.isEmpty();
    }

    public boolean inBounds(String field, int minBound, int maxBound){
        return field.length() >= minBound && field.length() <= maxBound;
    }

    public boolean inBounds(String field, int maxBound){
        return field.length() <= maxBound;
    }

    public boolean isMatch(String field, String regex, int flags){
        Pattern fieldPattern = Pattern.compile(regex, flags);
        Matcher matcher = fieldPattern.matcher(field);

        return matcher.matches();
    }

    public boolean isMatch(String field, String regex){
        Pattern fieldPattern = Pattern.compile(regex);
        Matcher matcher = fieldPattern.matcher(field);

        return matcher.matches();
    }

    public boolean isExist(String field){
        /**
         * TODO: Проверка на существование такого же поля в БД
         */

        return false;
    }
}
