package hexlet.code.domain.repository;

import hexlet.code.domain.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
