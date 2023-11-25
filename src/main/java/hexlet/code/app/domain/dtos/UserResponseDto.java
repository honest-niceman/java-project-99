package hexlet.code.app.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link hexlet.code.app.domain.model.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Instant createdAt;
}