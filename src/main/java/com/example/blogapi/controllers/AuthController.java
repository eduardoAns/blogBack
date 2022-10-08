package com.example.blogapi.controllers;


import com.example.blogapi.DAO.UsuarioDao;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.models.Rol;
import com.example.blogapi.models.Usuario;
import com.example.blogapi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;


    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public Map<String, String> login(@RequestBody Usuario usuario) {

        if(usuarioDao.obtenerUsuarioPorCredenciales(usuario) == null){
            throw new NotFoundException("Usuario o contraseña incorrectos","P-404");
        }
        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        return createToken(usuarioLogueado, usuarioLogueado.getId());
    }

    @RequestMapping(value = "api/validate-token", method = RequestMethod.GET)
    public Map<String, String> validateToken(@RequestHeader(value = "Authorization") String tokenUsuario) {

        System.out.println(tokenUsuario);
        String usuarioId = jwtUtil.getKey(tokenUsuario);
        Usuario usuarioLogueado = usuarioDao.getUsuario(Integer.parseInt(usuarioId));
        System.out.println(usuarioLogueado);

        return createToken(usuarioLogueado, Integer.parseInt(usuarioId));

    }

    @RequestMapping(value = "api/validtoken", method = RequestMethod.GET)
    public Map<String, String> validToken(@RequestHeader(value = "Authorization") String tokenUsuario) {

        String usuarioId = jwtUtil.getKey(tokenUsuario);
        Usuario usuarioLogueado = usuarioDao.getUsuario(Integer.parseInt(usuarioId));

        Map<String, String> map = new HashMap<String, String>();

        if (usuarioLogueado != null){

            map.put("id", String.valueOf(usuarioLogueado.getId()));
            map.put("rol", String.valueOf(usuarioLogueado.getIdRol()));

            System.out.println(map);
            return map;
        }

        return map;
    }




    private  Map<String, String> createToken(Usuario usuarioLogueado, int id) {

        String usuarioCorreo = usuarioLogueado.getEmail();
        String usuarioNombre = usuarioLogueado.getNombre();
        Integer usuarioRol = usuarioLogueado.getIdRol();

        Map<String, String> user = new HashMap<String, String>();

        user.put("correo",usuarioCorreo);
        user.put("rol", String.valueOf(usuarioRol));
        user.put("nombre",usuarioNombre);

        if (usuarioLogueado != null){

            String token = jwtUtil.create(String.valueOf(id), usuarioCorreo);
            user.put("token",token);

            System.out.println(user);
            return user;
        }

        return user;
    }
}
