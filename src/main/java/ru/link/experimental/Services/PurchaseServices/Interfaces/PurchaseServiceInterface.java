package ru.link.experimental.Services.PurchaseServices.Interfaces;

import ru.link.experimental.DTO.PurchaseDTO;

import java.util.UUID;

public interface PurchaseServiceInterface {

    void create(String name);

    void create(String name, UUID id);

    PurchaseDTO get(UUID id);
}
