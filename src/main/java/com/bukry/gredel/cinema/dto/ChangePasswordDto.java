package com.bukry.gredel.cinema.dto;

import com.bukry.gredel.cinema.validation.ValidPassword;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangePasswordDto {
    private String login;
    private String oldPassword;
    @ValidPassword
    private String newPassword;
}
