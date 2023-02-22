package com.example.blogapi.repository;

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
public class UserRepositoryImp implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private SocialMediaRepository socialMediaRepository;

    @Autowired
    private AvatarRepository avatarRepository;

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
