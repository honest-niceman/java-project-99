package hexlet.code.app.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link hexlet.code.app.domain.model.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto implements Serializable {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}