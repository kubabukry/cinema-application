package com.bukry.gredel.cinema.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationDto {
    private Long id;
    private Integer seat;
    private Long idPerson;
    private Long idSeance;
}
