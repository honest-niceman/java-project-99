package hexlet.code.app.domain.repository;

import hexlet.code.app.domain.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
