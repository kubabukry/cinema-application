package com.bukry.gredel.cinema.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenExpirationDto {
    private Boolean isNotExpired;
}
