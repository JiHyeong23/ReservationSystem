package sol.ReservationSystem.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sol.ReservationSystem.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;
    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;

    public void setFollower(User user) {
        this.follower = user;
    }
    public void setFollowing(User user) {
        this.following = user;
    }
}
