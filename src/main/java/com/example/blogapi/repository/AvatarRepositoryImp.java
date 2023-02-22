package com.example.blogapi.repository;

import com.example.blogapi.models.Avatar;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class AvatarRepositoryImp implements AvatarRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Avatar getAvatarByUserId(Integer idUser) {

        String query ="FROM Avatar WHERE id_user = :idUser";
        List<Avatar> lista = entityManager.createQuery(query)
                .setParameter("idUser", idUser)
                .getResultList();

        if(lista.isEmpty()){
            return null;
        }

        return lista.get(0);
    }

    @Override
    public void postAvatar(Avatar avatar) {

    }

    @Override
    public void updateAvatar(Avatar avatar) {
        entityManager.merge(avatar);
    }

    @Override
    public void deleteAvatarByUserId(Integer id) {
        String query ="DELETE FROM Avatar WHERE id_user = :idUser";
            entityManager.createQuery(query)
            .setParameter("idUser", id)
            .executeUpdate();
            
            
    }

    @Override
    public void deleteAvatar(String id) {
        Avatar avatar = entityManager.find(Avatar.class ,id);
        entityManager.remove(avatar);
    }
}
