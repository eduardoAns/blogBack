package com.example.blogapi.repository;

import com.example.blogapi.models.SocialMedia;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class SocialMediaRepositoryImp implements SocialMediaRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<SocialMedia> getSocialMediasByUserId(Integer idUser) {
        String query ="FROM SocialMedia WHERE id_user = :idUser";
        return entityManager.createQuery(query)
                .setParameter("idUser",idUser)
                .getResultList();
    }

    @Override
    public void createSocialMedia(SocialMedia socialMedia) {

    }

    @Override
    public void updateSocialMedia(SocialMedia socialMedia) {
        entityManager.merge(socialMedia);
    }

    @Override
    public void deleteSocialMedia(SocialMedia socialMedia) {
        entityManager.remove(socialMedia);
    }

}
