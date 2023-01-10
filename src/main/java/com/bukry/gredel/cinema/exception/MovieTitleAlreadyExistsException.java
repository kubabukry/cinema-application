package com.bukry.gredel.cinema.exception;

public class MovieTitleAlreadyExistsException extends RuntimeException{
    public MovieTitleAlreadyExistsException(String message){
        super(message);
    }
}
