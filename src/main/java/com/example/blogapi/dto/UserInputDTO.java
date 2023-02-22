package com.example.blogapi.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserInputDTO {

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellidoPaterno;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
