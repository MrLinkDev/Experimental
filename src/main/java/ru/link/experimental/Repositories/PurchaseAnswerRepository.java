package ru.link.experimental.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.link.experimental.Entities.PurchaseAnswerEntity;

import java.util.*;

public interface PurchaseAnswerRepository extends JpaRepository<PurchaseAnswerEntity, UUID> {

    PurchaseAnswerEntity findByQuestionId(UUID questionId);

}
