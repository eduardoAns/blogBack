package com.example.blogapi.DAO;

import com.example.blogapi.models.Avatar;
import com.example.blogapi.models.SocialMedia;
import com.example.blogapi.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private SocialMediaDao socialMediaDao;

    @Autowired
    private AvatarDao avatarDao;

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query ="FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Usuario getUsuario(Integer id) {

        return entityManager.find(Usuario.class ,id);
    }

    @Override
    public void deleteUsuario(Long id) {

        Usuario usuario = entityManager.find(Usuario.class ,id);
        entityManager.remove(usuario);
    }

    @Override
    public void postUsuario(Usuario usuario) {

        entityManager.persist(usuario);
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        System.out.println("entrando update usuario");

        Usuario detailUser = new Usuario();
        detailUser.setId(usuario.getId());
        detailUser.setNombre(usuario.getNombre());
        detailUser.setApellidoPaterno(usuario.getApellidoPaterno());
        detailUser.setDescripcion(usuario.getDescripcion());
        List<SocialMedia> socialMedias = usuario.getSocialMedias();


        updateUsuarioDetails(detailUser);
        System.out.println("usuario details actualizado");

        //AVATAR
        avatarDao.deleteAvatarByUserId(usuario.getId());

        if(!usuario.getAvatar().isEmpty()){
            Avatar avatar = usuario.getAvatar().get(0);
            entityManager.merge(avatar);
            System.out.println("avatar actualizado");
        }
        ;

        // redes sociales

        List<SocialMedia> socialMediaListBd = socialMediaDao.getSocialMediasByUserId(usuario.getId());
        System.out.println(socialMediaListBd);
        if(!socialMediaListBd.isEmpty()){
            List<SocialMedia> socialMediaListDelete = socialMediaListBd.stream().filter((social)->!socialMedias.contains(social)).collect(Collectors.toList());
            System.out.println("deletenado social media");
            System.out.println(socialMediaListDelete);

            socialMediaListDelete.forEach((socialMedia) -> entityManager.remove(socialMedia));

        }
        if(!socialMedias.isEmpty()){
            List<SocialMedia> socialMediaListInsert = socialMedias.stream().filter((social)->!socialMediaListBd.contains(social)).collect(Collectors.toList());
            System.out.println("insertnado social media");
            System.out.println(socialMediaListInsert);
            socialMediaListInsert.forEach((socialMedia) -> entityManager.merge(socialMedia));
        }



    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {

        String query ="FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if(lista.isEmpty()){
            return null;
        }
        String passwordHashed = lista.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())){
            return lista.get(0);
        }

        return null;

    }

    @Override
    public boolean existUsuarioById(Integer id) {

        Usuario usuario = entityManager.find(Usuario.class, id);
        return entityManager.contains(usuario);
    }

    @Override
    public void updateUsuarioDetails(Usuario usuario) {
        String query = "UPDATE Usuario SET nombre = :nombre, apellido_paterno = :apellido, descripcion = :descripcion WHERE id = :id";
        entityManager.createQuery(query)
                .setParameter("nombre", usuario.getNombre())
                .setParameter("apellido", usuario.getApellidoPaterno())
                .setParameter("descripcion", usuario.getDescripcion())
                .setParameter("id", usuario.getId())
                .executeUpdate();
    }


}
