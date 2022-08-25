package com.example.blogapi.controllers;


import com.example.blogapi.DAO.UsuarioDao;
import com.example.blogapi.exceptions.RecordNotFoundException;
import com.example.blogapi.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST} )
@RestController
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping(value="api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(){
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value="api/usuario/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) throws RecordNotFoundException {

        Usuario usuario = usuarioDao.getUsuario(id);
        if(usuario == null) {
            throw new RecordNotFoundException("Invalid employee id : " + id);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
}
