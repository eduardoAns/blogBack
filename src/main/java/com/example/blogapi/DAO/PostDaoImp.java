package com.example.blogapi.DAO;

import com.example.blogapi.models.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void postPost(Post post) {
        entityManager.persist(post);
        
    }

    @Override
    public void updatePost(Post post) {
        entityManager.merge(post);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = entityManager.find(Post.class ,id);
        entityManager.remove(post);
        
    }

    @Override
    public boolean existPostById(Integer id) {

        Post post = entityManager.find(Post.class, id);
        return entityManager.contains(post);

    }

    @Override
    public List<Post> getPostsByCategory(String category) {
        List<Post> listaType = getPosts();
        List<Post> response = listaType.stream().filter(p -> p.getCategoria().getNombre().contains(category)).collect(Collectors.toList());
        return response;
    }

    @Override
    public List<String> getIdPosts() {
        String query = "SELECT id FROM Post";
        List<Integer> lista = entityManager.createQuery(query).getResultList();
        List<String> result = new ArrayList<String>();
        for(int i=0;i<lista.size();i++){
            result.add(lista.get(i).toString());
        }
        return result;
    }
}
