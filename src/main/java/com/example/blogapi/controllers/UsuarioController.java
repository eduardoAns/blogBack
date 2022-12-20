package com.example.blogapi.controllers;


import com.example.blogapi.DAO.UsuarioDao;
import com.example.blogapi.exceptions.BadRequestException;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/usuario", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(){
        //if list is empty return Not Found
        if(usuarioDao.getUsuarios().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }

        return usuarioDao.getUsuarios();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/usuario/{id}" , method = RequestMethod.GET)
    public Usuario getUsuarioById(@PathVariable Integer id)  {

        if(!usuarioDao.existUsuarioById(id)) {
            throw new NotFoundException("id:"+id+" no encontrado, el usuario no existe","p-400");
        }
        return usuarioDao.getUsuario(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(value="api/usuario", method = RequestMethod.POST)
    public void postUsuario(@Valid @RequestBody Usuario usuario, BindingResult result){

        if(usuario.getId() != null){
            throw new BadRequestException("el id no es requerido","P-400");
        }

        if( result.hasFieldErrors("nombre")){
            throw new BadRequestException("el nombre es requerido","P-400");
        }

        if( result.hasFieldErrors("apellidoPaterno")){
            throw new BadRequestException("el apellido es requerido","P-400");
        }

        if( result.hasFieldErrors("email")){
            throw new BadRequestException("el email es requerido","P-400");
        }

        if( result.hasFieldErrors("password")){
            throw new BadRequestException("el password es requerido","P-400");
        }

        if( result.hasFieldErrors("sexo")){
            throw new BadRequestException("el sexo es requerido","P-400");
        }

        if( result.hasFieldErrors("idRol")){
            throw new BadRequestException("el id_rol es requerido","P-400");
        }

        if( result.hasFieldErrors("estado")){
            throw new BadRequestException("el estado es requerido","P-400");
        }

        if( result.hasFieldErrors("fechaCreacion")){
            throw new BadRequestException("la fecha de creacion es requerida","P-400");
        }

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.postUsuario(usuario);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value="api/usuario", method = RequestMethod.PUT)
    public void updateUsuario(@Valid @RequestBody Usuario usuario, BindingResult result){


        if( result.hasFieldErrors("nombre")){
            throw new BadRequestException("el nombre es requerido","P-400");
        }

        if( result.hasFieldErrors("apellidoPaterno")){
            throw new BadRequestException("el apellido es requerido","P-400");
        }

        if( result.hasFieldErrors("email")){
            throw new BadRequestException("el email es requerido","P-400");
        }

        if( result.hasFieldErrors("password")){
            throw new BadRequestException("el password es requerido","P-400");
        }

        if( result.hasFieldErrors("sexo")){
            throw new BadRequestException("el sexo es requerido","P-400");
        }

        if( result.hasFieldErrors("idRol")){
            throw new BadRequestException("el id_rol es requerido","P-400");
        }

        if( result.hasFieldErrors("estado")){
            throw new BadRequestException("el estado es requerido","P-400");
        }

        if( result.hasFieldErrors("fechaCreacion")){
            throw new BadRequestException("la fecha de creacion es requerida","P-400");
        }

        if( result.hasFieldErrors("descripcion")){
            throw new BadRequestException("la descripcion es requerida","P-400");
        }

        usuarioDao.updateUsuarioDetails(usuario);
    }



}
