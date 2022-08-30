package com.example.blogapi.exceptions;

import lombok.Getter;

public class NotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Not found exception (404)";

    @Getter
    private String code;
    public NotFoundException(String detail, String code) {
        super(DESCRIPTION + "|" + detail);
        this.code = code;
    }

}
