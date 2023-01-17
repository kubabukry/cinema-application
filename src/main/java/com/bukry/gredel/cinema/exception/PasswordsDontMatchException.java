package com.bukry.gredel.cinema.exception;

public class PasswordsDontMatchException extends RuntimeException{
    public PasswordsDontMatchException(String message){
        super(message);
    }
}
