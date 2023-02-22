package com.example.blogapi.repository;

import com.example.blogapi.models.Categoria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CategoryRepositoryImp implements CategoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Categoria> getCategorias() {
        String query ="FROM Categoria";
        return entityManager.createQuery(query).getResultList();

    }
}
