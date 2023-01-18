package com.bukry.gredel.cinema;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@OpenAPIDefinition(
		info = @Info(
				title = "Cinema API",
				version = "1.0",
				description = "Cinema application API documentation"
		),
		security = @SecurityRequirement(name = "authentication"),
		tags = {
				@Tag(name = "users", description = "Operations related to users, authorization and authentication"),
				@Tag(name = "rooms", description = "Operations related to rooms"),
				@Tag(name = "movies", description = "Operations related to movies"),
				@Tag(name = "reservations", description = "Operations related to reservations"),
				@Tag(name = "seances", description = "Operations related to seances")
		}
)

public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}



}
