package com.example.blogapi.exceptions;

import lombok.Getter;

public class BadRequestException extends RuntimeException{

    @Getter
    private String code;

    public BadRequestException(String detail, String code) {
        super(detail);
        this.code = code;
    }

}
