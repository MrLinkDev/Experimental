package ru.link.experimental.Services.PurchaseServices;

import ru.link.experimental.DTO.PurchaseQuestionDTO;

import java.util.*;

public interface PurchaseQuestionServiceInterface {

    void create(UUID purchaseId, String name, String content);

    void create(UUID id, UUID purchaseId, String name, String content);

    void update(UUID id, Optional<String> name, Optional<String> content);

    void delete(UUID id);

    PurchaseQuestionDTO get(UUID id);

    List<PurchaseQuestionDTO> getPage(int pageNumber, int pageSize);

}
