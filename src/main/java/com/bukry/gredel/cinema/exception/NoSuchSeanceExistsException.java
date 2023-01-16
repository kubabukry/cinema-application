package com.bukry.gredel.cinema.exception;

public class NoSuchSeanceExistsException extends RuntimeException{
    public NoSuchSeanceExistsException(String message){
        super(message);
    }
}
