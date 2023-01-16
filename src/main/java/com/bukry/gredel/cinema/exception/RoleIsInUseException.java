package com.bukry.gredel.cinema.exception;

public class RoleIsInUseException extends RuntimeException{
    public RoleIsInUseException(String message){
        super(message);
    }
}
