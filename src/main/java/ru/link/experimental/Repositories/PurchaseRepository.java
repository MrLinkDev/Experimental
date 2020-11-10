package ru.link.experimental.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.link.experimental.Entities.PurchaseEntity;

import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, UUID> {

}
