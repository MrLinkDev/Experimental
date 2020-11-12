package ru.link.experimental.Exceptions.PageExceptions;

import org.springframework.http.HttpStatus;
import ru.link.experimental.DTO.ErrorDTO;

/**
 * Класс исключения неверного размера страницы
 */
public class PageSizeException extends Exception{
    private static final String ERROR_MESSAGE = "Page size must not be less than one!";

    /**
     * Метод для получения ErrorModel исключения
     *
     * @return ErrorModel
     */
    public static ErrorDTO getErrorModel(){
        return new ErrorDTO(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
    }
}
