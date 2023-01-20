package com.bukry.gredel.cinema.dto;

import com.bukry.gredel.cinema.validation.ValidRoomName;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomCreationDto {
    @ValidRoomName
    private String name;
    @Min(value=1, message="seats: positive number, min 1 is required")
    @Max(value=256, message = "seats: positive number, max 256 allowed")
    private Integer seats;
}
