package com.bukry.gredel.cinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class MovieDto {
    Long id;
    String title;
    Integer duration;
}
