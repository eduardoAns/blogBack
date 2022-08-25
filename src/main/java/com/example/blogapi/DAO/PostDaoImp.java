package com.example.blogapi.DAO;

import com.example.blogapi.models.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PostDaoImp implements PostDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Post> getPosts() {
        String query ="FROM Post";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Post getPostById(Integer id) {
        return entityManager.find(Post.class, id);
    }
}
