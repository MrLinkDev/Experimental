package ru.link.experimental.Handlers;

import org.springframework.http.*;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.link.experimental.DTO.ErrorDTO;
import ru.link.experimental.Exceptions.PageExceptions.*;

/**
 * Обработчик исключений
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDTO errorModel = new ErrorDTO(status, "Required parameter is not present!");
        return new ResponseEntity(errorModel, status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleTypeMismatchException(MethodArgumentTypeMismatchException ex){
        ErrorDTO errorModel = new ErrorDTO(HttpStatus.BAD_REQUEST, "Wrong type of argument!");
        return new ResponseEntity(errorModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PageIndexException.class)
    protected ResponseEntity<Object> handlePageIndexException(){
        ErrorDTO errorModel = PageIndexException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(PageSizeException.class)
    protected ResponseEntity<Object> handlePageSizeException(){
        ErrorDTO errorModel = PageSizeException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(PageSortException.class)
    protected ResponseEntity<Object> handlePageSortException(){
        ErrorDTO errorModel = PageSortException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }


}
