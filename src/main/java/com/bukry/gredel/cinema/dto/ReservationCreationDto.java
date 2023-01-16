package com.bukry.gredel.cinema.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationCreationDto {
    private Integer seat;
    @NotNull
    private Long idPerson;
    @NotNull
    private Long idSeance;
}
