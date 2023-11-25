package hexlet.code.app;

import hexlet.code.app.domain.model.User;
import hexlet.code.app.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AppApplication(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User user = new User();
        user.setEmail("hexlet@example.com");
        user.setPassword(passwordEncoder.encode("qwerty"));
        userRepository.save(user);
        User user1 = new User();
        user1.setEmail("hexlet1@example.com");
        user1.setPassword(passwordEncoder.encode("qwerty"));
        userRepository.save(user1);
    }
}
