package com.bukry.gredel.cinema.dto;

import com.bukry.gredel.cinema.validation.ValidMovieTitle;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class MovieDto {
    Long id;
    @ValidMovieTitle
    String title;
    @Min(value=1, message="duration: positive number, min 1 is required")
    @Max(value=2880, message = "duration: positive number, max 2880 allowed")
    Integer duration;
}
