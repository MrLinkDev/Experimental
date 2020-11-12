package ru.link.experimental.Exceptions.PageExceptions;

import org.springframework.http.HttpStatus;
import ru.link.experimental.DTO.ErrorDTO;

/**
 * Класс исключения неверного индекса страницы
 */
public class PageIndexException extends Exception{
    private static final String ERROR_MESSAGE = "Page index must not be less than zero!";

    /**
     * Метод для получения ErrorModel исключения
     *
     * @return ErrorModel
     */
    public static ErrorDTO getErrorModel(){
        return new ErrorDTO(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
    }
}
