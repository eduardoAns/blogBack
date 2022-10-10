package com.example.blogapi.DAO;

import com.example.blogapi.models.Post;
import com.example.blogapi.models.Usuario;
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
    public List<Post> getPostsByTitle(String title) {
        List<Post> listaTitle = getPosts();
        List<Post> response = listaTitle.stream().filter(p -> p.getTitulo().contains(title)).collect(Collectors.toList());
        return response;
    }

    @Override
    public List<Post> getPostsByTag(String tag) {

        String queryTag = "SELECT id FROM Tag WHERE nombre = :tag";
        Integer idTag = (Integer) entityManager.createQuery(queryTag)
                .setParameter("tag", tag)
                .getSingleResult();

        String query = "SELECT idPost FROM Lista_tags WHERE idTag = :idTag";
        List<Integer> listaIdPosts = entityManager.createQuery(query)
                .setParameter("idTag", idTag)
                .getResultList();
        System.out.println(listaIdPosts);

        List<Post> listaPosts = new ArrayList<>();

        for (Integer idPost : listaIdPosts) {
            Post post = getPostById(idPost);
            listaPosts.add(post);
        }

        System.out.println(listaPosts);

        return listaPosts;
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
