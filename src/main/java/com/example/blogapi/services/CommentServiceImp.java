package com.example.blogapi.services;

import com.example.blogapi.repository.CommentRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.exceptions.BadRequestException;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Comentario;
import com.example.blogapi.utils.JWTUtil;
import com.example.blogapi.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;


@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ValidateUtil validateUtil;


    @Override
    public List<Comentario> getAllComments() {
        if(commentRepository.getComentarios().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }
        return commentRepository.getComentarios();
    }

    @Override
    public List<Comentario> getCommentByPostId(Integer id) {
        if(!postRepository.existPostById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el post no existe","p-404");
        }
        return commentRepository.getComentariosByPostId(id);
    }

    @Override
    public List<Comentario> getCommentByUserId(Integer id) {
        return commentRepository.getComentariosByUserId(id);
    }

    @Override
    public Comentario getCommentById(Integer id) {
        if(!commentRepository.existComentarioById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el comentario no existe","p-404");
        }
        return commentRepository.getComentarioById(id);
    }

    @Override
    public void createComment(Comentario comentario, BindingResult result) {

        //campos obligatorios
        List <String>commentProperties = List.of("idPost", "contenido", "nombre", "fechaCreacion", "estado" );
        validateUtil.validateJson(commentProperties, result);

        if(comentario.getId() != null){
            throw new BadRequestException("el id no es requerido","P-400");
        }

        if(!postRepository.existPostById(comentario.getIdPost())){
            throw new NotFoundException("id:"+comentario.getIdPost()+" no encontrado, el post no existe","p-404");
        }


        commentRepository.postComentario(comentario);
    }

    @Override
    public void updateComment(Comentario comentario, String userToken, BindingResult result) {

        //verifica existe un usuario con sesion iniciada y token valido
        List <String>commentProperties = List.of("idPost", "contenido", "fechaCreacion", "estado" );
        validateUtil.validateJson(commentProperties, result);

        validateUtil.validateUserSession(userToken, comentario.getIdUser());

        //if no existe el comentario return not found
        if(!commentRepository.existComentarioById(comentario.getId())){
            throw new NotFoundException("id:"+comentario.getId()+" no encontrado, el comentario no existe","p-404");
        }

        if(!postRepository.existPostById(comentario.getIdPost())){
            throw new NotFoundException("id:"+comentario.getIdPost()+" no encontrado, el post no existe","p-404");
        }

        commentRepository.updateComentario(comentario);
    }

    @Override
    public void deleteComment(Integer id) {
        if(!commentRepository.existComentarioById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el comentario no existe","p-404");
        }
        commentRepository.deleteComentario(id);
    }
}
