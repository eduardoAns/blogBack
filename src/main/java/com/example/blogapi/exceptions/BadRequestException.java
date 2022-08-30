package com.example.blogapi.exceptions;

import lombok.Getter;

public class BadRequestException extends RuntimeException{

    private static final String DESCRIPTION = "Bad request exception (400)";

    @Getter
    private String code;
    public BadRequestException(String detail, String code) {
        super(DESCRIPTION + "|" + detail);
        this.code = code;
    }

}
