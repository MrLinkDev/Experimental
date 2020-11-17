package ru.link.experimental.Services.PurchaseServices;

import ru.link.experimental.DTO.PurchaseAnswerDTO;
import ru.link.experimental.Exceptions.*;

import java.util.*;

public interface PurchaseAnswerServiceInterface {

    void create(UUID questionId, String content, Boolean publicity) throws AnswerException;

    void update(UUID id, String content, Boolean publicity) throws AnswerException;

    void delete(UUID id) throws AnswerException;

    PurchaseAnswerDTO get(UUID id) throws AnswerException;

}
