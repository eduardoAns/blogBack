package com.example.blogapi.DAO;

import com.example.blogapi.models.SocialMedia;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class SocialMediaDaoImp implements SocialMediaDao{

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
    public void postAvatar(SocialMedia avatar) {

    }

    @Override
    public void updateAvatar(SocialMedia avatar) {

    }

    @Override
    public void deleteAvatar(Integer id) {

    }
}
