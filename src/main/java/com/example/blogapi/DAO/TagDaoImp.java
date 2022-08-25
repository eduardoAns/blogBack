package com.example.blogapi.DAO;

import com.example.blogapi.models.Tag;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class TagDaoImp implements TagDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Tag> getTags() {
        String query ="FROM Tag";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Tag getTagById(Integer id) {
        return entityManager.find(Tag.class, id);
    }
}
