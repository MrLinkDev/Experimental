package ru.link.experimental.Services.PurchaseServices;

import ru.link.experimental.DTO.PurchaseQuestionDTO;
import ru.link.experimental.Exceptions.PageExceptions.*;

import java.util.*;

public interface PurchaseQuestionServiceInterface {

    void create(UUID purchaseId, String name, String content);

    void update(UUID id, String name, String content);

    void delete(UUID id);

    PurchaseQuestionDTO get(UUID id);

    List<PurchaseQuestionDTO> getPage(int pageIndex, int pageSize) throws PageIndexException, PageSizeException;

}
