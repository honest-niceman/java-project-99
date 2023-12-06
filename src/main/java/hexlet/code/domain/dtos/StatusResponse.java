package hexlet.code.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link hexlet.code.domain.model.Status}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse implements Serializable {
    private Long id;
    private String name;
    private String slug;
    private Instant createdAt;
}
