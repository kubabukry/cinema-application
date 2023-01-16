package com.bukry.gredel.cinema.exception;

public class NoSuchRoomExistsException extends RuntimeException{
    public NoSuchRoomExistsException(String message){
        super(message);
    }
}
