package hexlet.code.app.domain.repository;

import hexlet.code.app.domain.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findBySlug(String slug);
}
