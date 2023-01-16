package com.bukry.gredel.cinema.dto;

import com.bukry.gredel.cinema.validation.ValidEmail;
import com.bukry.gredel.cinema.validation.ValidLogin;
import com.bukry.gredel.cinema.validation.ValidPassword;
import lombok.Data;

@Data
public class PersonCreationDto {
    @ValidLogin
    private String login;
    @ValidEmail
    private String email;
    @ValidPassword
    private String password;
}
