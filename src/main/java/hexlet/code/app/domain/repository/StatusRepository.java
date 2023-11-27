package hexlet.code.app.domain.repository;

import hexlet.code.app.domain.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}