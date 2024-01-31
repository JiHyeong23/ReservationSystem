package sol.ReservationSystem.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sol.ReservationSystem.validation.ValidationGroups;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserSignUpDto {
    @NotBlank(message = "이름은 필수 입력값입니다.", groups = ValidationGroups.NotEmptyGroup.class)
    private String name;
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력값입니다.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}",
            message = "비밀번호는 영문과 숫자 조합으로 8-16자리까지 가능합니다.", groups = ValidationGroups.PatternCheckGroup.class)
    private String password;
    private String profileImage;
    private String description;
}
