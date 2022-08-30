package com.example.blogapi.models;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ErrorMessage {
    @Getter
    private String exception;
    @Getter
    private String message;
    @Getter
    private String path;
    @Getter
    private String code;

    public ErrorMessage(Exception exception, String path, String code) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
        this.code = code;
    }


}
