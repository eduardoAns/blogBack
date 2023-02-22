package com.example.blogapi.exceptions;

import lombok.Getter;

public class NotFoundException extends RuntimeException {

    @Getter
    private String code;

    public NotFoundException(String detail, String code) {
        super(detail);
        this.code = code;
    }

}
