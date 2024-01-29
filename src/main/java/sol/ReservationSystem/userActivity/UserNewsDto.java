package sol.ReservationSystem.userActivity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserNewsDto {
    private String username;
    private String contentType;
    private Long contentId;
    private LocalDateTime createdAt;
    private String contentedBy;
    private String type;
}
