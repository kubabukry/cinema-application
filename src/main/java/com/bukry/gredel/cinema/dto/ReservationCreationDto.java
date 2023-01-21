package com.bukry.gredel.cinema.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationCreationDto {
    @NotNull
    private Integer seat;
    private Long idPerson;
    @NotNull
    private Long idSeance;
}
