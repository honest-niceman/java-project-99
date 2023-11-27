package hexlet.code.app.domain.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link hexlet.code.app.domain.model.Status}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusRequest implements Serializable {
    private String name;
    private String slug;
}