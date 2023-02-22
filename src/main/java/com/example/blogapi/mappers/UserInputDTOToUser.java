package com.example.blogapi.mappers;

import com.example.blogapi.dto.UserInputDTO;
import com.example.blogapi.models.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserInputDTOToUser implements IMapper<UserInputDTO, Usuario>{
    @Override
    public Usuario map(UserInputDTO userCreate) {
        Usuario usuario  = new Usuario();
        Integer rolNewUser = 2;

        usuario.setNombre(userCreate.getNombre());
        usuario.setApellidoPaterno(userCreate.getApellidoPaterno());
        usuario.setEmail(userCreate.getEmail());
        usuario.setPassword(userCreate.getPassword());
        usuario.setFechaCreacion(LocalDateTime.now().toString());
        usuario.setIdRol(rolNewUser);
        usuario.setEstado("activo");
        usuario.setDescripcion("usuario activo de este blog");
        usuario.setSexo("");
        return usuario;
    }
}
