package com.example.blogapi.controllers;


import com.example.blogapi.DAO.ComentarioDao;

import com.example.blogapi.exceptions.BadRequestException;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Comentario;
import com.example.blogapi.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST} )
@RestController
public class ComentarioController {

    @Autowired
    private ComentarioDao comentarioDao;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/comentarios", method = RequestMethod.GET)
    public List<Comentario> getComentarios(){
        if(comentarioDao.getComentarios().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }
        return comentarioDao.getComentarios();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/comentario/{id}" , method = RequestMethod.GET)
    public Comentario getComentarioById(@PathVariable Integer id){
        if(!comentarioDao.existComentarioById(id)){
                throw new NotFoundException("id:"+id+" no encontrado, el comentario no existe","p-404");
        }
        return comentarioDao.getComentarioById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(value="api/comentario" , method = RequestMethod.POST)
    public void postComentario(@Valid @RequestBody Comentario comentario, BindingResult result){

        if(comentario.getId() != null){
            throw new BadRequestException("el id no es requerido","P-400");
        }
        //if idPost is null or vacio return bad request
        if( result.hasFieldErrors("idPost")){
            throw new BadRequestException("el idPost es requerido","P-400");
        }
        //if contenido is null or vacio return bad request
        if( result.hasFieldErrors("contenido")){
            throw new BadRequestException("el contenido es requerido","P-400");
        }
        //if titulo is null or vacio return bad request
        if(result.hasFieldErrors( "titulo")){
            throw new BadRequestException("el titulo es requerido","P-400");
        }
        //if nombre is null or vacio return bad request
        if(result.hasFieldErrors("nombre")){
            throw new BadRequestException("el nombre es requerido","P-400");
        }
        //if fechaCreacion is null or vacio return bad request
        if(result.hasFieldErrors("fechaCreacion")){
            throw new BadRequestException("la fecha de creacion es requerida","P-400");
        }
        //if estado is null or vacio return bad request
        if(result.hasFieldErrors("estado")){
            throw new BadRequestException("el estado es requerido","P-400");
        }

        comentarioDao.postComentario(comentario);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @RequestMapping(value="api/comentario" , method = RequestMethod.PUT)
    public void updateComentario(@Valid @RequestBody Comentario comentario, BindingResult result){

        //if no existe el comentario return not found
        if(!comentarioDao.existComentarioById(comentario.getId())){
            throw new NotFoundException("id:"+comentario.getId()+" no encontrado, el comentario no existe","p-404");
        }
        if(result.hasFieldErrors("id")){
            throw new BadRequestException("el id es requerido","P-400");
        }
        //if idPost is null or vacio return bad request
        if( result.hasFieldErrors("idPost")){
            throw new BadRequestException("el idPost es requerido","P-400");
        }
        //if contenido is null or vacio return bad request
        if( result.hasFieldErrors("contenido")){
            throw new BadRequestException("el contenido es requerido","P-400");
        }
        //if titulo is null or vacio return bad request
        if(result.hasFieldErrors("titulo")){
            throw new BadRequestException("el titulo es requerido","P-400");
        }
        //if nombre is null or vacio return bad request
        if(result.hasFieldErrors("nombre")){
            throw new BadRequestException("el nombre es requerido","P-400");
        }
        //if fechaCreacion is null or vacio return bad request
        if(result.hasFieldErrors("fechaCreacion")){
            throw new BadRequestException("la fecha de creacion es requerida","P-400");
        }
        //if estado is null or vacio return bad request
        if(result.hasFieldErrors("estado")){
            throw new BadRequestException("el estado es requerido","P-400");
        }

        comentarioDao.updateComentario(comentario);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @RequestMapping(value="api/comentario/{id}" , method = RequestMethod.DELETE)
    public void deleteComentario(@PathVariable Integer id){
        if(!comentarioDao.existComentarioById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el comentario no existe","p-404");
        }
        comentarioDao.deleteComentario(id);
    }
}











