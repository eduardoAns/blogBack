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
}
