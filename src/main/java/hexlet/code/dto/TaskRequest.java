package hexlet.code.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link hexlet.code.model.Task}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest implements Serializable {
    private String title;
    private Long index;
    private String content;
    private String status;
    @JsonProperty("assignee_id")
    private Long assigneeId;
    private Set<Long> taskLabelIds;
}
