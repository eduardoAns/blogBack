package com.example.blogapi.DAO;

import com.example.blogapi.models.Image;
import com.example.blogapi.models.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ImageDaoImp implements ImageDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Image> getImages() {
        String query ="FROM Image";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Image getImageById(Integer id) {

        return entityManager.find(Image.class, id);
    }

    @Override
    public void postImage(Image image) {
        entityManager.persist(image);

    }

    @Override
    public void updateImage(Image image) {
        entityManager.merge(image);
    }

    @Override
    public void deleteImage(Integer id) {
        Post post = entityManager.find(Post.class ,id);
        entityManager.remove(post);

    }

    @Override
    public boolean existImageById(Integer id) {

        Post post = entityManager.find(Post.class, id);
        return entityManager.contains(post);

    }
}
