package com.example.blogapi.services;

import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.models.Usuario;
import com.example.blogapi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImp implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Map<String, String> loginUser(Usuario usuario) {
        if(userRepository.obtenerUsuarioPorCredenciales(usuario) == null){
            throw new NotFoundException("Usuario o contraseña incorrectos","P-404");
        }
        Usuario usuarioLogueado = userRepository.obtenerUsuarioPorCredenciales(usuario);
        System.out.println("-----login------");
        return createToken(usuarioLogueado, usuarioLogueado.getId());
    }

    @Override
    public Map<String, String> refreshToken(String tokenUsuario) {
        System.out.println(tokenUsuario);
        String usuarioId = jwtUtil.getKey(tokenUsuario);
        Usuario usuarioLogueado = userRepository.getUsuario(Integer.parseInt(usuarioId));
        System.out.println("-----validate-token------");
        return createToken(usuarioLogueado, Integer.parseInt(usuarioId));
    }

    @Override
    public Map<String, String> validToken(String tokenUsuario) {
        String usuarioId = jwtUtil.getKey(tokenUsuario);
        Usuario usuarioLogueado = userRepository.getUsuario(Integer.parseInt(usuarioId));

        Map<String, String> map = new HashMap<String, String>();

        if (usuarioLogueado != null){

            map.put("id", String.valueOf(usuarioLogueado.getId()));
            map.put("idRol", String.valueOf(usuarioLogueado.getIdRol()));

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

        if (usuarioLogueado != null){

            user.put("correo",usuarioCorreo);
            user.put("idRol", String.valueOf(usuarioRol));
            user.put("nombre",usuarioNombre);
            String token = jwtUtil.create(String.valueOf(id), usuarioCorreo);
            user.put("token",token);

            System.out.println(user);
            return user;
        }

        return user;
    }
}
