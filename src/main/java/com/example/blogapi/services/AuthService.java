package com.example.blogapi.services;

import com.example.blogapi.models.Usuario;

import java.util.Map;

public interface AuthService {

    Map<String, String> loginUser (Usuario usuario);

    Map<String, String> refreshToken (String tokenUsuario);

    Map<String, String> validToken (String tokenUsuario);
}
