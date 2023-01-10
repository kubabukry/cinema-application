package com.bukry.gredel.cinema.exception;

public class LoginAlreadyExistsException extends RuntimeException{
    public LoginAlreadyExistsException(String message){
        super(message);
    }
}
