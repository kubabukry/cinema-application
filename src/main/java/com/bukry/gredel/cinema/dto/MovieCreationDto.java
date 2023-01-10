package com.bukry.gredel.cinema.dto;

import com.bukry.gredel.cinema.validation.ValidMovieTitle;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieCreationDto {
    @ValidMovieTitle
    private String title;
    @Min(value=1, message="duration: positive number, min 1 is required")
    @Max(value=2880, message = "duration: positive number, max 2880 allowed")
    private Integer duration;       //todo czy to powinien być Integer?
}
