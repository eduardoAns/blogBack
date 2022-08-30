package com.example.blogapi.DAO;

import com.example.blogapi.models.Comentario;
import com.example.blogapi.models.Tag;
import com.example.blogapi.models.Usuario;

import java.util.List;

public interface ComentarioDao {
    List<Comentario> getComentarios();

    Comentario getComentarioById(Integer id);

    void postComentario(Comentario comentario);

    void updateComentario(Comentario comentario);

    void deleteComentario(Integer id);

    boolean existComentarioById(Integer id);

}
