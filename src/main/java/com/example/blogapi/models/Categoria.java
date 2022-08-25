package com.example.blogapi.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
@ToString
@EqualsAndHashCode
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private List<Post> posts;

}
