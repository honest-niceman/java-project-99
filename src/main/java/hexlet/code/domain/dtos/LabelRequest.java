package hexlet.code.domain.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link hexlet.code.domain.model.Label}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelRequest implements Serializable {
    @Size(min = 3, max = 1000)
    private String name;
}
