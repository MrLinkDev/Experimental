package ru.link.experimental.Exceptions.PageExceptions;

import org.springframework.http.HttpStatus;
import ru.link.experimental.DTO.ErrorDTO;

/**
 * Класс исключения неверного параметра сортировки элементов страницы
 */
public class PageSortException extends Exception{
    private static final String ERROR_MESSAGE = "Illegal page sorting arg!";

    /**
     * Метод для получения ErrorModel исключения
     *
     * @return ErrorModel
     */
    public static ErrorDTO getErrorModel(){
        return new ErrorDTO(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
    }
}
