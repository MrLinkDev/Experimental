package ru.link.experimental.Services.PurchaseServices;

import ru.link.experimental.DTO.PurchaseQuestionDTO;

import java.util.UUID;

public interface PurchaseQuestionServiceInterface {

    void create(UUID purchaseId, String name, String content);

    void create(UUID id, UUID purchaseId, String name, String content);

    PurchaseQuestionDTO get(UUID id);

}
