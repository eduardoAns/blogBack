package com.example.blogapi.DAO;

import com.example.blogapi.models.Tag;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
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
    public void postTag(Tag tag) {
        entityManager.persist(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        entityManager.merge(tag);
    }

    @Override
    public void deleteTag(Integer id) {
        Tag tag = entityManager.find(Tag.class ,id);
        entityManager.remove(tag);
    }

    @Override
    public Tag getTagById(Integer id) {
        return entityManager.find(Tag.class, id);
    }

    @Override
    public boolean existTagById(Integer id) {

        Tag tag = entityManager.find(Tag.class, id);
        return entityManager.contains(tag);
    }

    @Override
    public void postTagInPost(Integer idTag, Integer idPost ) {

        // post id_tag and id_post in lista_tags
        String query ="INSERT INTO lista_tags (id_tag, id_post) VALUES ("+idTag+", "+idPost+")";
        entityManager.createNativeQuery(query).executeUpdate();

    }

    @Override
    public void updateTagInPost(Integer idTag, Integer idPost ) {

        // post id_tag and id_post in lista_tags
        String query ="UPDATE ListaTags SET id_tag = :idTag WHERE id_post = :idPost";

        entityManager.createQuery(query)
                .setParameter("idTag", idTag)
                .setParameter("idPost", idPost).executeUpdate();

    }

    @Override
    public Tag getTagByName(String nombre) {
        String query = "FROM Tag WHERE nombre = :nombre";
        List<Tag> tag = entityManager.createQuery(query).setParameter("nombre", nombre).getResultList();
        return tag.get(0);
    }

    @Override
    public boolean existTagByName(String nombre) {
        String query = "FROM Tag WHERE nombre = :nombre";
        List<Tag> tag = entityManager.createQuery(query).setParameter("nombre", nombre).getResultList();
        if(tag.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public boolean existPostInListTag(Integer idPost, Integer idTag) {
        String query = "FROM ListaTags WHERE id_post = :idPost AND id_tag = :idTag";
        List<Tag> tag = entityManager.createQuery(query)
                        .setParameter("idPost", idPost)
                        .setParameter("idTag", idTag).getResultList();
        if(tag.isEmpty()){
            return false;
        }
        return true;
    }
}
