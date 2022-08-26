package com.example.blogapi.models;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comentarios")
@ToString @EqualsAndHashCode
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @Getter @Setter @Column(name = "titulo")
    private String titulo;

    @Getter @Setter @Column(name = "contenido")
    private String contenido;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    // @ManyToOne
    // @Getter @Setter @JoinColumn(name = "id_post")
    // private Post post;

    @Getter @Setter @Column(name = "fecha_creacion")
    private String fechaCreacion;

    @Getter @Setter @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    @Getter @Setter @Column(name = "estado")
    private String estado;

}
