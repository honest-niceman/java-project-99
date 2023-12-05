package hexlet.code.app.domain.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link hexlet.code.app.domain.model.Label}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelResponse implements Serializable {
    private Long id;
    @Size(min = 3, max = 1000)
    private String name;
    private Instant createdAt;
}