package com.bukry.gredel.cinema.dto;

import com.bukry.gredel.cinema.validation.ValidRoleName;
import lombok.Data;

@Data
public class RoleNameDto {
    @ValidRoleName
    private String name;
}
