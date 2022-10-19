package com.example.blogapi.DAO;

import com.example.blogapi.models.Tag;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

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
    public void deleteTagInPost(Integer idTag, Integer idPost) {
        String query ="DELETE FROM lista_tags WHERE id_tag = "+idTag+" AND id_post = "+idPost;
        entityManager.createNativeQuery(query).executeUpdate();
    }

    @Override
    public List<Tag> getTagsByPostId (Integer idPost){
        String query = "SELECT id_tag FROM lista_tags WHERE id_post = :idPost";
        List<Integer> listaIdTags = entityManager.createNativeQuery(query).setParameter("idPost", idPost).getResultList();
        List<Tag> listaTags = getTags();
        List<Tag> response = listaTags.stream().filter(t -> listaIdTags.contains(t.getId())).collect(Collectors.toList());
        return response;
    }

}
