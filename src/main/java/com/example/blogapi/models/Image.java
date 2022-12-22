package com.example.blogapi.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "images")
@ToString
public class Image {

    @Id
    @Getter @Setter @Column(name = "id")
    private String id;

    @Getter @Setter @Column(name = "id_post")
    @NotNull
    private Integer idPost;

    @Getter @Setter @Column(name = "url")
    @NotEmpty
    private String url;

    // @ManyToOne
    // @JoinColumn(name = "id_post")
    // private Post post;

}
