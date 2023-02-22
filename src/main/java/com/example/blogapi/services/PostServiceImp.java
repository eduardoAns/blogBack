package com.example.blogapi.services;

import com.example.blogapi.repository.ImageRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.exceptions.BadRequestException;
import com.example.blogapi.exceptions.NotFoundException;
import com.example.blogapi.models.Image;
import com.example.blogapi.models.Post;
import com.example.blogapi.models.Tag;
import com.example.blogapi.repository.TagRepository;
import com.example.blogapi.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ValidateUtil validateUtil;

    @Override
    public List<Post> getAllPosts() {
        /*if(postDao.getPosts().isEmpty()){
            throw new RequestException("P-500", HttpStatus.INTERNAL_SERVER_ERROR,"Error al traer los datos, json vacio");
        }
        */
        return postRepository.getPosts();
    }

    @Override
    public List<Post> getPostByCategory(String category) {
        return  postRepository.getPostsByCategory(category);
    }

    @Override
    public List<Post> getPostByTag(String tag) {
        return  postRepository.getPostsByTag(tag);
    }

    @Override
    public List<Post> getPostByTitle(String title) {
        return  postRepository.getPostsByTitle(title);
    }

    @Override
    public List<Post> getPostByUser(Integer id) {
        return postRepository.getPostsByUserId(id);
    }

    @Override
    public Post getPostById(String id) {

        if(id.equals("new")) {
            return new Post();
        }

        if(id.equals("undefined")) {
            return new Post();
        }

        Integer postId = Integer.parseInt(id);

        if(!postRepository.existPostById(postId)){
            throw new NotFoundException("id:"+id+" no encontrado, el post no existe","p-404");
        }

        return postRepository.getPostById(postId);
    }

    @Override
    public void createPost(Post post, BindingResult result) {

        List <String> postProperties = List.of("titulo", "subtitulo", "contenido", "fechaCreacion", "estado", "idUsuario", "idCategoria") ;
        validateUtil.validateJson(postProperties, result);

        if(post.getId() != null){
            throw new BadRequestException("el id no es requerido","P-400");
        }

        List<Image> images = post.getImages();
        List<Tag> tags = post.getTags();

        Post postForm = new Post();
        postForm.setTitulo(post.getTitulo());
        postForm.setSubtitulo(post.getSubtitulo());
        postForm.setContenido(post.getContenido());
        postForm.setFechaCreacion(post.getFechaCreacion());
        postForm.setEstado(post.getEstado());
        postForm.setIdUsuario(post.getIdUsuario());
        postForm.setCategoria(post.getCategoria());

        postRepository.createPost(postForm);
        Integer postId = postRepository.getPostIdByTitle(post.getTitulo());

        for(int i=0;i<images.size();i++){
            images.get(i).setIdPost(postId);
            imageRepository.updateImage(images.get(i));
        }

        for(int i=0;i<tags.size();i++){
            System.out.println(tags.get(i));
            if(!tagRepository.existTagByName(tags.get(i).getNombre())){
                tagRepository.postTag(tags.get(i));
                Integer tagId = tags.get(i).getId();
                tagRepository.postTagInPost(tagId, postId);
            }else {
                Integer tagId = tagRepository.getTagByName(tags.get(i).getNombre()).getId();
                tagRepository.postTagInPost(tagId, postId);
            }
        }
    }

    @Override
    public void updatePost(Post post, BindingResult result) {
        List <String> postProperties = List.of("id", "titulo", "subtitulo", "contenido", "fechaCreacion", "estado", "idUsuario", "idCategoria") ;
        validateUtil.validateJson(postProperties, result);

        List<Image> images = post.getImages();
        List<Tag> tags = post.getTags();



        postRepository.updateDetailsPost(post);
        Integer postId = postRepository.getPostIdByTitle(post.getTitulo());

        for(int i=0;i<images.size();i++){
            images.get(i).setIdPost(postId);
            imageRepository.updateImage(images.get(i));
        }
        //tengo array de tags y quiero actualizar, algunos de estos tags pueden ser nuevos y otros tags tienen que ser eliminados
        //primero tengo que obtener los tags que ya estan en la base de datos
        List<Tag> tagsInPost = tagRepository.getTagsByPostId(postId);
        //tengo que obtener los tags que ya estan en la base de datos y que no estan en el array de tags que me llega
        List<Tag> tagsToDelete = tagsInPost.stream().filter(tag -> !tags.contains(tag)).collect(Collectors.toList());
        //tengo que obtener los tags que estan en el array de tags que me llega y que no estan en la base de datos
        List<Tag> tagsToInsert = tags.stream().filter(tag -> !tagsInPost.contains(tag)).collect(Collectors.toList());

        //elimino los tags que no estan en el array de tags que me llega
        for(int i=0;i<tagsToDelete.size();i++){
            tagRepository.deleteTagInPost(postId, tagsToDelete.get(i).getId());
        }

        //inserto los tags que estan en el array de tags que me llega y que no estan en la base de datos
        for(int i=0;i<tagsToInsert.size();i++){
            if(!tagRepository.existTagByName(tagsToInsert.get(i).getNombre())){
                tagRepository.postTag(tagsToInsert.get(i));
                Integer tagId = tagsToInsert.get(i).getId();
                tagRepository.postTagInPost(tagId, postId);
            }else {
                Integer tagId = tagRepository.getTagByName(tagsToInsert.get(i).getNombre()).getId();
                tagRepository.postTagInPost(tagId, postId);
            }
        }
    }

    @Override
    public void deletePost(Integer id) {
        if(!postRepository.existPostById(id)){
            throw new NotFoundException("id:"+id+" no encontrado, el post no existe","p-404");
        }
        postRepository.deletePost(id);
    }

}
