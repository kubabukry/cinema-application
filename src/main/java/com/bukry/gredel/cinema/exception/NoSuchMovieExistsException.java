package com.bukry.gredel.cinema.exception;

public class NoSuchMovieExistsException extends RuntimeException{
    public NoSuchMovieExistsException(String message){
        super(message);
    }
}
