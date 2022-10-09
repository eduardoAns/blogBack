package com.example.blogapi.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Posts")
@EqualsAndHashCode
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @NotNull @Getter @Setter @Column(name = "id_usuario")
    private Integer idUsuario;

    @NotEmpty @Getter @Setter @Column(name = "titulo")
    private String titulo;

    @NotEmpty @Getter @Setter @Column(name = "subtitulo")
    private String subtitulo;

    @NotEmpty @Getter @Setter @Column(name = "contenido")
    private String contenido;

    @NotEmpty @Getter @Setter @Column(name = "fecha_creacion")
    private String fechaCreacion;

    @NotEmpty @Getter @Setter @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    @ManyToOne
    @Getter @Setter @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @NotEmpty @Getter @Setter @Column(name = "estado")
    private String estado;

    // @ManyToOne
    // @JoinColumn(name = "id_usuario")
    // private Usuario usuario;

    @ManyToMany @Setter @Getter
    @JoinTable(name="lista_tags", joinColumns =@JoinColumn(name ="id_post" ) , inverseJoinColumns = @JoinColumn(name ="id_tag" ))
    private List<Tag> tags;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_post", referencedColumnName = "id")
    @Setter @Getter
    private List<Comentario> comentarios;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_post", referencedColumnName = "id")
    @Setter @Getter
    private List<Image> images;


}
