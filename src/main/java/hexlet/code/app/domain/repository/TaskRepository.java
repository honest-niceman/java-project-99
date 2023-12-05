package hexlet.code.app.domain.repository;

import hexlet.code.app.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}