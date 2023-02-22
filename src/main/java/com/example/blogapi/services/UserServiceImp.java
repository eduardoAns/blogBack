package com.example.blogapi.services;

import com.example.blogapi.dto.UserInputDTO;
import com.example.blogapi.mappers.UserInputDTOToUser;
import com.example.blogapi.models.Avatar;
import com.example.blogapi.models.SocialMedia;
import com.example.blogapi.repository.AvatarRepository;
import com.example.blogapi.repository.SocialMediaRepository;
import com.example.blogapi.repository.UserRepository;
import com.example.blogapi.exceptions.BadRequestException;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.exceptions.RequestException;
import com.example.blogapi.models.Usuario;
import com.example.blogapi.utils.JWTUtil;
import com.example.blogapi.utils.ValidateUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AvatarRepository avatarRepository;
    @Autowired
    private SocialMediaRepository socialMediaRepository;
    @Autowired
    private ValidateUtil validateUtil;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserInputDTOToUser mapper;

    @Override
    public List<Usuario> getAllUsers() {

        if(userRepository.getUsuarios().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }

        return userRepository.getUsuarios();
    }

    @Override
    public Usuario getUserById(Integer id) {

        if(!userRepository.existUsuarioById(id)) {
            throw new NotFoundException("id:"+id+" no encontrado, el usuario no existe","p-404");
        }
        return userRepository.getUsuario(id);
    }

    @Override
    public void createUser(UserInputDTO userInputDTO, BindingResult result) {

        List <String> userProperties = List.of("nombre", "apellidoPaterno", "email", "password") ;
        validateUtil.validateJson(userProperties, result);

        Usuario user = mapper.map(userInputDTO);

        if(user.getId() != null){
            throw new BadRequestException("el id no es requerido","P-400");
        }

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        userRepository.postUsuario(user);

    }

    @Override
    public void updateUser(Usuario usuario, BindingResult result) {

        List <String> usuarioProperties = List.of("nombre", "apellidoPaterno", "email", "password", "sexo", "idRol", "estado", "fechaCreacion","descripcion" ) ;
        validateUtil.validateJson(usuarioProperties, result);
        // Detalles de usuario
        Usuario detailUser = new Usuario();
        detailUser.setId(usuario.getId());
        detailUser.setNombre(usuario.getNombre());
        detailUser.setApellidoPaterno(usuario.getApellidoPaterno());
        detailUser.setDescripcion(usuario.getDescripcion());

        userRepository.updateUsuarioDetails(detailUser);

        //AVATAR

        avatarRepository.deleteAvatarByUserId(usuario.getId());

        if(!usuario.getAvatar().isEmpty()){
            Avatar avatar = usuario.getAvatar().get(0);
            avatarRepository.updateAvatar(avatar);
            System.out.println("avatar actualizado");
        }
        ;

        // redes sociales
        List<SocialMedia> socialMedias = usuario.getSocialMedias();
        List<SocialMedia> socialMediaListBd = socialMediaRepository.getSocialMediasByUserId(usuario.getId());

        if(!socialMediaListBd.isEmpty()){
            List<SocialMedia> socialMediaListDelete = socialMediaListBd.stream().filter((social)->!socialMedias.contains(social)).collect(Collectors.toList());
            socialMediaListDelete.forEach((socialMedia) -> socialMediaRepository.deleteSocialMedia(socialMedia));
        }

        if(!socialMedias.isEmpty()){
            List<SocialMedia> socialMediaListInsert = socialMedias.stream().filter((social)->!socialMediaListBd.contains(social)).collect(Collectors.toList());
            socialMediaListInsert.forEach((socialMedia) -> socialMediaRepository.updateSocialMedia(socialMedia));
        }
    }



}
