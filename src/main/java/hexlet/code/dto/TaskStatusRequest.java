package hexlet.code.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link hexlet.code.model.TaskStatus}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusRequest implements Serializable {
    private String name;
    private String slug;
}
