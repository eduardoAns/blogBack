package com.example.blogapi.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "lista_tags")
@ToString
public class Lista_tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @Getter @Setter @Column(name = "id_tag")
    private Integer idTag;

    @Getter @Setter @Column(name = "id_post")
    private Integer idPost;
}
