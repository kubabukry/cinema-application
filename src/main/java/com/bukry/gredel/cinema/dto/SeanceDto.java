package com.bukry.gredel.cinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class SeanceDto {
    private Long id;
    private Boolean isPublicated;
    private MovieDto movie;
    private RoomDto room;
    private Instant startDate;
    private Instant endDate;
    private Integer availableSeats;
    private List<Integer> freeSeats;
}
