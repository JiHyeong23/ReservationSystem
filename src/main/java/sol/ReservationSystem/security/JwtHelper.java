package sol.ReservationSystem.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component
public class JwtHelper {
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 20;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;
    private Environment env;

    public String getEmailFromJwtToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(env.getProperty("jwt.key.secret")));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(env.getProperty("jwt.key.secret")));
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResponseToken createToken(String username) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "access");
        map.put("name", username);
        SecretKey accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(env.getProperty("jwt.key.secret")));

        String accessToken = Jwts.builder()
                .setClaims(map).setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(accessKey, SignatureAlgorithm.HS512).compact();

        String refreshToken = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(accessKey, SignatureAlgorithm.HS512).compact();

        //Token token = new Token(username, accessToken, refreshToken);
        //tokenRepository.save(token);

        return ResponseToken.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
