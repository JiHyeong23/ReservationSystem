package sol.ReservationSystem.user;

import lombok.*;
import sol.ReservationSystem.post.Post;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Setter
    private LocalDateTime updatedAt;
    @Setter
    private LocalDateTime lastLogin;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public void updateUser(String name, String description) {
        this.name = Objects.requireNonNullElse(name, this.name);
        this.description = Objects.requireNonNullElse(description, this.description);
    }

    public void setNewPassword(String password) {
        this.password = password;
    }
}
