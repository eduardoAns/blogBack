package com.example.blogapi.DAO;

import com.example.blogapi.models.Avatar;
import com.example.blogapi.models.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class AvatarDaoImp implements AvatarDao {

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

    }

    @Override
    public void deleteAvatar(Integer id) {

    }
}
