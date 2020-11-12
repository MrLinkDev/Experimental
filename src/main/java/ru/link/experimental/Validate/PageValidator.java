package ru.link.experimental.Validate;

import org.springframework.stereotype.Component;
import ru.link.experimental.Exceptions.PageExceptions.*;

@Component
public class PageValidator {

    public void checkPageIndex(int pageIndex) throws PageIndexException {
        if (pageIndex < 0) {
            throw new PageIndexException();
        }
    }

    public void checkPageSize(int pageSize) throws PageSizeException {
        if (pageSize < 1) {
            throw new PageSizeException();
        }
    }

}
