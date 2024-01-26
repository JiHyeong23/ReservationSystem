package sol.ReservationSystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class SecurityController {
    private final JwtHelper jwtHelper;

    @PostMapping
    public ResponseEntity reAccess(HttpServletRequest request) {
        String refreshToken = getToken(request);
        ResponseToken responseToken = null;

        if(jwtHelper.validateJwtToken(refreshToken)) {
            String username = jwtHelper.getEmailFromJwtToken(refreshToken);
             responseToken = jwtHelper.createToken(username);
        }
        return ResponseEntity.ok(responseToken);
    }

    private String getToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
