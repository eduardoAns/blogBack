package com.example.blogapi.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class ErrorDTO {
    private String code;
    private String exception;
    private String message;
    private String path;

    public ErrorDTO(Exception exception, String path, String code) {
        this.code = code;
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
    }
}
