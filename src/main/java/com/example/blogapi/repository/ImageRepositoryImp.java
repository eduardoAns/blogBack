package com.example.blogapi.repository;

import com.example.blogapi.models.Image;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ImageRepositoryImp implements ImageRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Image> getImages() {
        String query ="FROM Image";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Image getImageById(String id) {

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
    public void deleteImage(String id) {
        Image image = entityManager.find(Image.class ,id);
        entityManager.remove(image);

    }

    @Override
    public boolean existImageById(String id) {

        Image image = entityManager.find(Image.class, id);
        return entityManager.contains(image);

    }
}
