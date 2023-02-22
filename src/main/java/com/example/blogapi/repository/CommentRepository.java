package com.example.blogapi.repository;

import com.example.blogapi.models.Comentario;

import java.util.List;

public interface CommentRepository {
    List<Comentario> getComentarios();

    Comentario getComentarioById(Integer id);

    void postComentario(Comentario comentario);

    void updateComentario(Comentario comentario);

    void deleteComentario(Integer id);

    boolean existComentarioById(Integer id);

    List<Comentario> getComentariosByPostId(Integer id);

    List<Comentario> getComentariosByUserId(Integer id);
}
