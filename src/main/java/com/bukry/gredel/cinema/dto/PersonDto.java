package com.bukry.gredel.cinema.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDto {
    private Long id;
    private String email;
    private String login;
    private RoleDto role;
}
