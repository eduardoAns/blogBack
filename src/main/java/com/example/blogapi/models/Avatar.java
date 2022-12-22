package com.example.blogapi.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "avatar")
@ToString
public class Avatar {

    @Id
    @Getter @Setter @Column(name = "id")
    private String id;

    @Getter @Setter @Column(name = "url")
    private String url;

    @Getter @Setter @Column(name = "id_user")
    private Integer idUsuario;
}
