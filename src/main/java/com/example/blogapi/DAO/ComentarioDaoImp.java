package com.example.blogapi.DAO;

import com.example.blogapi.models.Comentario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ComentarioDaoImp implements ComentarioDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Comentario> getComentarios() {
        String query ="FROM Comentario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Comentario getComentarioById(Integer id) {
        return entityManager.find(Comentario.class, id);
    }

    @Override
    public void postComentario(Comentario comentario) {
        entityManager.persist(comentario);
        
    }

    @Override
    public void updateComentario(Comentario comentario) {
        entityManager.merge(comentario);
    }

    @Override
    public void deleteComentario(Integer id) {
        Comentario comentario = entityManager.find(Comentario.class ,id);
        entityManager.remove(comentario);
    }

    @Override
    public boolean existComentarioById(Integer id) {
        Comentario comentario = entityManager.find(Comentario.class, id);
        return entityManager.contains(comentario);
    }

    @Override
    public List<Comentario> getComentariosByPostId(Integer id) {
        //return array comentarios by id_post
        String query ="FROM Comentario WHERE id_post = "+id;
        return entityManager.createQuery(query).getResultList();
    }
}
