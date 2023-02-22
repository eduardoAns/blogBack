package com.example.blogapi.services;

import com.example.blogapi.dto.UserInputDTO;
import com.example.blogapi.models.Usuario;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {

    List<Usuario> getAllUsers();

    Usuario getUserById(Integer id);

    void createUser(UserInputDTO usuario, BindingResult result);

    void updateUser(Usuario usuario, BindingResult result);
}
