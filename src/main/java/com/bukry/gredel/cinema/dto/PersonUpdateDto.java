package com.bukry.gredel.cinema.dto;

import com.bukry.gredel.cinema.validation.ValidEmail;
import com.bukry.gredel.cinema.validation.ValidLogin;
import com.bukry.gredel.cinema.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonUpdateDto {
    @ValidLogin
    private String login;
    @ValidEmail
    private String email;
}
