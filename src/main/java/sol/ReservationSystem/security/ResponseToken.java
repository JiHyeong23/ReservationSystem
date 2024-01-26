package sol.ReservationSystem.security;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class ResponseToken {
    private String accessToken;
    private String refreshToken;
}