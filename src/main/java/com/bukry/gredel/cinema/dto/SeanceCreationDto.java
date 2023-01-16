package com.bukry.gredel.cinema.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class SeanceCreationDto {
    private Long idMovie;
    private Long idRoom;
    private Instant startDate;
    private Instant endDate;
    private Boolean isPublicated;
}
