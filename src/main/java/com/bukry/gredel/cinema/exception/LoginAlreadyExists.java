package com.bukry.gredel.cinema.exception;

public class LoginAlreadyExists extends RuntimeException{
    public LoginAlreadyExists(String message){
        super(message);
    }
}
