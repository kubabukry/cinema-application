package com.bukry.gredel.cinema.configuration;

import com.bukry.gredel.cinema.exception.NoSuchPersonExistsException;
import com.bukry.gredel.cinema.repository.PersonRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@ConditionalOnProperty(prefix = "cinema", name = "security", havingValue = "true")
public class AuthenticationConfig {

    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationConfig(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> personRepository.findByLogin(username)
                .orElseThrow(() -> new NoSuchPersonExistsException(
                        "No such user with login: "+username+" exists"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
