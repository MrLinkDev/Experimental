package ru.link.experimental.Services.PurchaseServices;

import ru.link.experimental.DTO.PurchaseAnswerDTO;

import java.util.*;

public interface PurchaseAnswerServiceInterface {

    void create(UUID questionId, String content, boolean publicity);

    void create(UUID id, UUID questionId, String content, boolean publicity);

    void update(UUID id, Optional<String> content, Optional<Boolean> publicity);

    void delete(UUID id);

    PurchaseAnswerDTO get(UUID id);

}
