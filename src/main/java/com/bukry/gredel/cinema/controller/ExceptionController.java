package com.bukry.gredel.cinema.controller;


import com.bukry.gredel.cinema.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ExceptionHandler(value = RoleAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleRoleAlreadyExists(RoleAlreadyExists e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchRoleExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchRoleExists(NoSuchRoleExists e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = LoginAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleLoginAlreadyExists(LoginAlreadyExists e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = EmailAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEmailAlreadyExists(EmailAlreadyExists e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchPersonExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchPersonExistsException(NoSuchPersonExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(
           MethodArgumentNotValidException e) {

        List<String> messages = e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), messages.toString());
    }
}
