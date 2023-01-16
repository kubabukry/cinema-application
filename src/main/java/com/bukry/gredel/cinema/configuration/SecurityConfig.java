package com.bukry.gredel.cinema.configuration;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import java.util.logging.Logger;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "cinema", name = "security", havingValue = "true")
public class SecurityConfig {

    private static final Logger LOGGER = Logger.getLogger( SecurityConfig.class.getName() );

    @PostConstruct
    void postCreation(){
        LOGGER.info("Security enabled!");
    }
}
