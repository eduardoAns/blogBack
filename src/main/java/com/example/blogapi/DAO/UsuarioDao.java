package com.example.blogapi.DAO;

import com.example.blogapi.models.Avatar;
import com.example.blogapi.models.SocialMedia;
import com.example.blogapi.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

    Usuario getUsuario(Integer id);

    void deleteUsuario(Long id);

    void postUsuario(Usuario usuario);

    void updateUsuario(Usuario usuario);

    void updateUsuarioDetails(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

    boolean existUsuarioById(Integer id);
}
