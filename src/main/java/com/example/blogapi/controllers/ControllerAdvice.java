package com.example.blogapi.controllers;

import com.example.blogapi.exceptions.BadRequestException;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.ErrorDTO;
import com.example.blogapi.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorMessage> requestExceptionHandler(HttpServletRequest request,RequestException ex){
        ErrorMessage error = new ErrorMessage(ex, request.getRequestURI(), ex.getCode());
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, BadRequestException ex){
        return new ErrorMessage(ex,request.getRequestURI(),ex.getCode() );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public ErrorMessage notFound(HttpServletRequest request, NotFoundException ex){
        return new ErrorMessage(ex,request.getRequestURI(),ex.getCode() );
    }




    // @ExceptionHandler(BadRequestException.class)
    // public ResponseEntity<?>BadRequestException(Exception ex){
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    // }



}
