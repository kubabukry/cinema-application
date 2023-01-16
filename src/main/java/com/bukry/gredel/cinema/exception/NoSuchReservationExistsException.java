package com.bukry.gredel.cinema.exception;

public class NoSuchReservationExistsException extends RuntimeException{
    public NoSuchReservationExistsException(String message){
        super(message);
    }
}
