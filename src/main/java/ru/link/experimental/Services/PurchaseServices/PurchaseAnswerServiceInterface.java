package ru.link.experimental.Services.PurchaseServices;

import ru.link.experimental.DTO.PurchaseAnswerDTO;

import java.util.UUID;

public interface PurchaseAnswerServiceInterface {

    void create(UUID questionId, String content);

    void create(UUID id, UUID questionId, String content);

    PurchaseAnswerDTO get(UUID id);

}
