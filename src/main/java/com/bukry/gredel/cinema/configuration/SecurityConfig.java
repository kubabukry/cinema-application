package com.bukry.gredel.cinema.configuration;

import com.bukry.gredel.cinema.model.Role;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.logging.Logger;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "cinema", name = "security", havingValue = "true")
public class SecurityConfig {

    private static final Logger LOGGER = Logger.getLogger( SecurityConfig.class.getName() );
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf()     //todo nie działa z and().cors()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/persons/auth/**", "/swagger-ui/**", "/v3/api-docs/**",
                        "/seances/all", "seances/single/**",
                        "/rooms/all", "/rooms/single/**", "/movies/all", "/movies/single/**")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/seances/create/**", "/seances/update",
                        "/seances/delete/**", "/room/create", "/room/update", "/room/delete/**",
                        "/movies/create", "/movies/update", "/movies/delete/**", "/persons/all")
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @PostConstruct
    void postCreation(){
        LOGGER.info("Security enabled!");
    }
}
