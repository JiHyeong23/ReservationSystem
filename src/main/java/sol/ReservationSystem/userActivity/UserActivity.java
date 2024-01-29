package sol.ReservationSystem.userActivity;

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
public class UserActivity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private Activity activity;
    private Long activityId;
    @ManyToOne
    @JoinColumn(name = "contented_by")
    private User contentedBy;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
