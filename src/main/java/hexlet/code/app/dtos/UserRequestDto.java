package hexlet.code.app.dtos;

import hexlet.code.app.model.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link User}
 */
public final class UserRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private final String firstName;
    private final String lastName;
    private final String email;
    private String password;

    /**
     *
     */
    public UserRequestDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserRequestDto) obj;
        return Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password);
    }

    @Override
    public String toString() {
        return "UserRequestDto[" +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "email=" + email + ", " +
                "password=" + password + ']';
    }

}
