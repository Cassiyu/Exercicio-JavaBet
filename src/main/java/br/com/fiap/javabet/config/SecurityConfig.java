package br.com.fiap.javabet.config;

import br.com.fiap.javabet.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, UserService userService) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().authenticated())
            .oauth2Login(login -> login
                .userInfoEndpoint(userInfo -> userInfo.userService(userService))
                .permitAll()
            )

        ;
        return http.build();

    }
}
