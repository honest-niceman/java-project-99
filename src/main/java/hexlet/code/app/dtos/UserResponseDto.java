package hexlet.code.app.dtos;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link hexlet.code.app.model.User}
 */
public record UserResponseDto(Long id, String firstName, String lastName, String email,
                              Instant createdAt) implements Serializable {
}
