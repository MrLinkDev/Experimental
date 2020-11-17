package ru.link.experimental.Services.PurchaseServices;

import ru.link.experimental.DTO.PurchaseQuestionDTO;
import ru.link.experimental.Exceptions.PageExceptions.*;
import ru.link.experimental.Exceptions.QuestionException;

import java.util.*;

public interface PurchaseQuestionServiceInterface {

    void create(UUID purchaseId, String name, String content) throws QuestionException;

    void update(UUID id, String name, String content) throws QuestionException;

    void delete(UUID id) throws QuestionException;

    PurchaseQuestionDTO get(UUID id) throws QuestionException;

    List<PurchaseQuestionDTO> getPage(int pageIndex, int pageSize) throws PageIndexException, PageSizeException;

}
