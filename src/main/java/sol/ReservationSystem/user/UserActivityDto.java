package sol.ReservationSystem.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class UserActivityDto {
    private Long id;
    private String body;
    private LocalDateTime created_at;
    private Long post_id;
    private Long user_id;
    private Long like_count;
    private String title;
    private Long comment_id;
    private Long follower_id;
    private Long following_id;
}
