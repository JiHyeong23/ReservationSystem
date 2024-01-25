package sol.ReservationSystem.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpDto {
    private String name;
    private String email;
    private String password;
    private String profileImage;
    private String description;
}
