package com.example.blogapi.controllers;


import com.example.blogapi.models.Usuario;
import com.example.blogapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;


    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @PostMapping(value = "/login")
    public Map<String, String> login(@RequestBody Usuario usuario) {

        return authService.loginUser(usuario);
    }

    @GetMapping(value = "/validate-token")
    public Map<String, String> validateToken(@RequestHeader(value = "Authorization") String tokenUsuario) {

        return authService.refreshToken(tokenUsuario);

    }

    @GetMapping(value = "/validtoken")
    public Map<String, String> validToken(@RequestHeader(value = "Authorization") String tokenUsuario) {

        return authService.validToken(tokenUsuario);
    }

}
