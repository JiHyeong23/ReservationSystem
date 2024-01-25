package sol.ReservationSystem.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sol.ReservationSystem.user.UserRepository;
import sol.ReservationSystem.user.UserService;
import sol.ReservationSystem.user.dto.UserLoginDto;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private UserService userService;
    private JwtHelper jwtHelper;
    private UserRepository userRepository;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, JwtHelper jwtHelper, UserRepository userRepository) {
        super(authenticationManager);
        this.userService = userService;
        this.jwtHelper = jwtHelper;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginDto userLoginDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.getEmail(),
                            userLoginDto.getPassword(),
                            new ArrayList<>()
                    )
            );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        String username = ((User)authResult.getPrincipal()).getUsername();
        sol.ReservationSystem.user.User user = userService.findByEmail(username);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        String accessToken = jwtHelper.createAccessToken(username);
        String refreshToken = jwtHelper.createRefreshToken(username);

        ResponseToken responseToken = new ResponseToken();
        responseToken.setAccessToken(accessToken);
        responseToken.setRefreshToken(refreshToken);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); //크로스오리진
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseToken));
    }
}
