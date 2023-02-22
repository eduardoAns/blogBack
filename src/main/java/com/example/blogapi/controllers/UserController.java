package com.example.blogapi.controllers;


import com.example.blogapi.dto.UserInputDTO;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Usuario;
import com.example.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<Usuario> getUsuarios(){

        return userService.getAllUsers();

    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id)  {

        return userService.getUserById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    public void postUsuario(@Valid @RequestBody UserInputDTO usuario, BindingResult result){
        userService.createUser(usuario, result);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping
    public void updateUsuario(@Valid @RequestBody Usuario usuario, BindingResult result){
        userService.updateUser(usuario, result);

    }



}
