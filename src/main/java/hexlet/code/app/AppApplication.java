package hexlet.code.app;

import hexlet.code.app.domain.model.Status;
import hexlet.code.app.domain.model.User;
import hexlet.code.app.domain.repository.StatusRepository;
import hexlet.code.app.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StatusRepository statusRepository;

    public AppApplication(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          StatusRepository statusRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.statusRepository = statusRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User user = new User();
        user.setEmail("hexlet@example.com");
        user.setFirstName("Hex");
        user.setLastName("Let");
        user.setPassword(passwordEncoder.encode("qwerty"));
        userRepository.save(user);
        User user1 = new User();
        user1.setEmail("1");
        user1.setPassword(passwordEncoder.encode("1"));
        user1.setFirstName("Hex1");
        user1.setLastName("Let1");
        userRepository.save(user1);

        Status draft = new Status();
        draft.setName("Draft");
        draft.setSlug("draft");

        Status toReview = new Status();
        toReview.setName("To Review");
        toReview.setSlug("to_review");

        Status toBeFixed = new Status();
        toBeFixed.setName("To Be Fixed");
        toBeFixed.setSlug("to_be_fixed");

        Status toPublish = new Status();
        toPublish.setName("To Publish");
        toPublish.setSlug("to_publish");

        Status published = new Status();
        published.setName("Published");
        published.setSlug("published");

        statusRepository.saveAll(List.of(
                draft,
                toReview,
                toBeFixed,
                toPublish,
                published
        ));
    }
}
