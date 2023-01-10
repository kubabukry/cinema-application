package com.bukry.gredel.cinema.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDto {
    private Long id;
    private String name;
    private Integer seats;
}
