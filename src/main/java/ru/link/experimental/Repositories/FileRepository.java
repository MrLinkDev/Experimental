package ru.link.experimental.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.link.experimental.Entities.FileEntity;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {

}
