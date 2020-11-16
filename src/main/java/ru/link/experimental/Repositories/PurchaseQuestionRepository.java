package ru.link.experimental.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.link.experimental.Entities.PurchaseQuestionEntity;

import java.util.*;

public interface PurchaseQuestionRepository extends JpaRepository<PurchaseQuestionEntity, UUID> {

    Optional<PurchaseQuestionEntity> findByPurchaseId(UUID purchaseId);

    List<PurchaseQuestionEntity> findAllByPurchaseId(UUID purchaseId);

    PurchaseQuestionEntity getById(UUID purchaseId);

}
