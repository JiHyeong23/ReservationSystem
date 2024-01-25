package sol.ReservationSystem.user;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Setter
    private String description;
    @Setter
    private String profileImage;
    @Setter
    private LocalDateTime createdAt;
    @Setter
    private LocalDateTime updatedAt;
    @Setter
    private LocalDateTime lastLogin;

    @Builder
    public User(Long id, String name, String email, String password, String profileImage, String description, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastLogin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
    }

    public void updateUser(String name, String description) {
        this.name = Objects.requireNonNullElse(name, this.name);
        this.description = Objects.requireNonNullElse(description, this.description);
    }

    public void setNewPassword(String password) {
        this.password = password;
    }
}
