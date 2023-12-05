package hexlet.code.app.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 1)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Size(min = 1)
    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}
