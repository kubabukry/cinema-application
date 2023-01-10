package com.bukry.gredel.cinema.exception;

public class RoomAlreadyExistsException extends RuntimeException{
    public RoomAlreadyExistsException(String message){
        super(message);
    }
}
