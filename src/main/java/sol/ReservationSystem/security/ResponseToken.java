package sol.ReservationSystem.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ResponseToken {
    private String accessToken;
    private String refreshToken;
}