package com.example.blogapi.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tags")
@ToString
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @NotEmpty @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    //@ManyToMany(mappedBy = "tags")
    //private List<Post> posts;
}
