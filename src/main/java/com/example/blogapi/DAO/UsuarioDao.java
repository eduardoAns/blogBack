package com.example.blogapi.DAO;

import com.example.blogapi.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

    Usuario getUsuario(Integer id);

    void deleteUsuario(Long id);

    void postUsuario(Usuario usuario);



    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
