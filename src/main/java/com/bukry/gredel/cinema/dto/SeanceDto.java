package com.bukry.gredel.cinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class SeanceDto {
    private Long id;
    private Boolean isPublicated;
    private Long idMovie;
    private Long idRoom;
    private Instant startDate;
    private Instant endDate;
    private Integer availableSeats;
}
