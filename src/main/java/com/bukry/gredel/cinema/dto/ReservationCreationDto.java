package com.bukry.gredel.cinema.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationCreationDto {
    private Integer seat;
    @NotNull
    private Long idPerson;
    @NotNull
    private Long idSeance;
}
