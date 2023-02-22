package com.example.blogapi.services;

import com.example.blogapi.models.Comentario;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CommentService {

    List<Comentario> getAllComments ();

    List<Comentario> getCommentByPostId(Integer id);

    List<Comentario> getCommentByUserId(Integer id);

    Comentario getCommentById (Integer id);

    void createComment(Comentario comentario, BindingResult result);

    void updateComment (Comentario comentario, String userToken, BindingResult result);

    void deleteComment (Integer id);


}
