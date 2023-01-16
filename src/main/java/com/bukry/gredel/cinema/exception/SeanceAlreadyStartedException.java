package com.bukry.gredel.cinema.exception;

public class SeanceAlreadyStartedException extends RuntimeException{
    public SeanceAlreadyStartedException(String message){
        super(message);
    }
}
