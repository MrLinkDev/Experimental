package ru.link.experiment.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.link.experiment.persist.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
