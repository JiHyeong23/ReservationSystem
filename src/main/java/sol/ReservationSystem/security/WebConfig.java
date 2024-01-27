package sol.ReservationSystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import sol.ReservationSystem.user.UserRepository;
import sol.ReservationSystem.user.UserService;

@Configuration
@EnableWebSecurity
public class WebConfig {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtHelper jwtHelper;
    private final UserRepository userRepository;
    private LogoutHandler logoutHandler;

    public WebConfig(UserService userService, JwtHelper jwtHelper, UserRepository userRepository, LogoutHandler logoutHandler) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
        this.userRepository = userRepository;
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean //필터들을 관리한다 + 시큐리티 설정 등등
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        try {
            AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
            authenticationManagerBuilder.userDetailsService(userService);
            authenticationManager = authenticationManagerBuilder.build();

            httpSecurity
                    .csrf().disable()
                    .authorizeHttpRequests()
                    .antMatchers(HttpMethod.GET, "/")
                    .permitAll()
                    .antMatchers(HttpMethod.POST, "/")
                    .permitAll()
                    .and()
                    .addFilter(getAuthenticationFilter(httpSecurity.getSharedObject(AuthenticationConfiguration.class)))
            ;
            httpSecurity
                    .authenticationManager(authenticationManager)
                    .sessionManagement() //세션에 대한 규칙 설정
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ;
            httpSecurity
                    .logout()
                    .logoutSuccessHandler(logoutHandler)
                    .invalidateHttpSession(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpSecurity.build();
    }

    private AuthenticationFilter getAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(getAuthenticationManager(authenticationConfiguration), userService, jwtHelper, userRepository);
        authenticationFilter.setAuthenticationManager(getAuthenticationManager(authenticationConfiguration));
        return authenticationFilter;
    }
}
