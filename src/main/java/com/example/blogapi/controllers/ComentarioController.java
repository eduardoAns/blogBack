package com.example.blogapi.controllers;


import com.example.blogapi.DAO.ComentarioDao;

import com.example.blogapi.models.Comentario;
import com.example.blogapi.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST} )
@RestController
public class ComentarioController {

    @Autowired
    private ComentarioDao comentarioDao;

    @RequestMapping(value="api/comentarios", method = RequestMethod.GET)
    public List<Comentario> getComentarios(){
        return comentarioDao.getComentarios();
    }

    @RequestMapping(value="api/comentario/{id}" , method = RequestMethod.GET)
    public Comentario getComentarioById(@PathVariable Integer id){
        return comentarioDao.getComentarioById(id);
    }
}
