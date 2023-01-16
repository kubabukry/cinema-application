package com.bukry.gredel.cinema.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data      // with RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private Integer statusCode;
    private String message;

    public ErrorResponse(String message){
        super();
        this.message = message;
    }
}
