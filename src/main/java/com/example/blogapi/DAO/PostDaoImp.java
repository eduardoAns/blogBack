package com.example.blogapi.DAO;

import com.example.blogapi.models.Image;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.Tag;
import com.example.blogapi.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TagDao tagDao;

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

    private Integer getId(String titulo) {
        String query = "SELECT id FROM Post WHERE titulo = :titulo";
        List<Integer> id = entityManager.createQuery(query).setParameter("titulo", titulo).getResultList();
        return id.get(0);
    }

    @Override
    public void postPost(Post post, List<Image> images, List<Tag> tags) {
        entityManager.merge(post);
        Integer postId = getId(post.getTitulo());

        for(int i=0;i<images.size();i++){
            images.get(i).setIdPost(postId);
            entityManager.merge(images.get(i));
        }

        for(int i=0;i<tags.size();i++){
            System.out.println(tags.get(i));
            if(!tagDao.existTagByName(tags.get(i).getNombre())){
                tagDao.postTag(tags.get(i));
                Integer tagId = tags.get(i).getId();
                tagDao.postTagInPost(tagId, postId);
            }else {
                Integer tagId = tagDao.getTagByName(tags.get(i).getNombre()).getId();
                tagDao.postTagInPost(tagId, postId);
            }
        }
    }

    private void update(Post post) {
        String query = "UPDATE Post SET titulo = :titulo, subtitulo = :subtitulo, contenido = :contenido, fecha_actualizacion = :fecha, id_categoria = :idCategoria WHERE id = :id";
            entityManager.createQuery(query)
            .setParameter("titulo", post.getTitulo())
            .setParameter("subtitulo", post.getSubtitulo())
            .setParameter("contenido", post.getContenido())
            .setParameter("fecha", post.getFechaCreacion())
            .setParameter("idCategoria", post.getCategoria().getId())
            .setParameter("id", post.getId()).executeUpdate();
    }

    @Override
    public void updatePost(Post post, List<Image> images, List<Tag> tags) {
        update(post);
        Integer postId = getId(post.getTitulo());

        for(int i=0;i<images.size();i++){
                images.get(i).setIdPost(postId);
                entityManager.merge(images.get(i));
        }
        //tengo array de tags y quiero actualizar, algunos de estos tags pueden ser nuevos y otros tags tienen que ser eliminados
        //primero tengo que obtener los tags que ya estan en la base de datos
        List<Tag> tagsInPost = tagDao.getTagsByPostId(postId);
        //tengo que obtener los tags que ya estan en la base de datos y que no estan en el array de tags que me llega
        List<Tag> tagsToDelete = tagsInPost.stream().filter(tag -> !tags.contains(tag)).collect(Collectors.toList());
        //tengo que obtener los tags que estan en el array de tags que me llega y que no estan en la base de datos
        List<Tag> tagsToInsert = tags.stream().filter(tag -> !tagsInPost.contains(tag)).collect(Collectors.toList());

        //elimino los tags que no estan en el array de tags que me llega
        for(int i=0;i<tagsToDelete.size();i++){
            tagDao.deleteTagInPost(postId, tagsToDelete.get(i).getId());
        }

        //inserto los tags que estan en el array de tags que me llega y que no estan en la base de datos
        for(int i=0;i<tagsToInsert.size();i++){
            if(!tagDao.existTagByName(tagsToInsert.get(i).getNombre())){
                tagDao.postTag(tagsToInsert.get(i));
                Integer tagId = tagsToInsert.get(i).getId();
                tagDao.postTagInPost(tagId, postId);
            }else {
                Integer tagId = tagDao.getTagByName(tagsToInsert.get(i).getNombre()).getId();
                tagDao.postTagInPost(tagId, postId);
            }
        }

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

    @Override
    public List<Post> getPostsByUserId(Integer id) {
        String query = "FROM Post WHERE idUsuario = :id";
        List<Post> lista = entityManager.createQuery(query)
                .setParameter("id", id)
                .getResultList();
        return lista;
    }
}
