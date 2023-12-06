package hexlet.code.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link hexlet.code.model.TaskStatus}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusResponse implements Serializable {
    private Long id;
    private String name;
    private String slug;
    private Instant createdAt;
}
