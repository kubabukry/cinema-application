package com.bukry.gredel.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RoomDto {
    private Long id;
    private String name;
    private Integer seats;
}
