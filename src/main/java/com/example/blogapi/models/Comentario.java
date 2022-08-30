package com.example.blogapi.models;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "comentarios")
@ToString @EqualsAndHashCode
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @Getter @Setter @Column(name = "id_post")
    @NotNull
    private Integer idPost;

    @Getter @Setter @Column(name = "titulo")
    @NotEmpty @NotBlank @NotNull
    private String titulo;

    @NotEmpty @Getter @Setter @Column(name = "contenido")
    private String contenido;

    @NotEmpty @Getter @Setter @Column(name = "nombre")
    private String nombre;

    // @ManyToOne
    // @Getter @Setter @JoinColumn(name = "id_post")
    // private Post post;

    @NotEmpty @Getter @Setter @Column(name = "fecha_creacion")
    private String fechaCreacion;

    @Getter @Setter @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    @NotEmpty @Getter @Setter @Column(name = "estado")
    private String estado;

    public Comentario() {
    }

}
