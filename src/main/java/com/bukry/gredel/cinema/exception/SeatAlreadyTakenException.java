package com.bukry.gredel.cinema.exception;

public class SeatAlreadyTakenException extends RuntimeException{
    public SeatAlreadyTakenException(String message){
        super(message);
    }
}
