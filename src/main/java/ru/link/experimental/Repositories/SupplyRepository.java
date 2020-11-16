package ru.link.experimental.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.link.experimental.Entities.SupplyEntity;

import java.util.UUID;

public interface SupplyRepository extends JpaRepository<SupplyEntity, UUID> {

}
