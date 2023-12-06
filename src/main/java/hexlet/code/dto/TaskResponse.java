package hexlet.code.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link hexlet.code.model.Task}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse implements Serializable {
    private Long id;
    private String title;
    private Long index;
    private String content;
    private String status;
    @JsonProperty("assignee_id")
    private Long assigneeId;
    private Instant createdAt;
    private Set<Long> taskLabelIds;
}
