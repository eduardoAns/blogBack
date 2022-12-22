package com.example.blogapi.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "social_media")
@ToString
public class SocialMedia {

    @Id
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @Getter @Setter @Column(name = "url")
    private String url;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "id_user")
    private Integer idUsuario;
}
