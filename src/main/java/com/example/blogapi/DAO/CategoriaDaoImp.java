package com.example.blogapi.DAO;

import com.example.blogapi.models.Categoria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CategoriaDaoImp implements CategoriaDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Categoria> getCategorias() {
        String query ="FROM Categoria";
        return entityManager.createQuery(query).getResultList();

    }
}
