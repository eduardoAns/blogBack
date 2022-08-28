package com.example.blogapi.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;
}
