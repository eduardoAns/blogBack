package com.example.blogapi.repository;

import com.example.blogapi.models.Usuario;

import java.util.List;

public interface UserRepository {

    List<Usuario> getUsuarios();

    Usuario getUsuario(Integer id);

    void deleteUsuario(Long id);

    void postUsuario(Usuario usuario);

    void updateUsuarioDetails(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

    boolean existUsuarioById(Integer id);
}
