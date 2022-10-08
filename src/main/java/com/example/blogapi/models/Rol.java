package com.example.blogapi.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "roles")
@ToString
@EqualsAndHashCode
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @Getter @Setter @Column(name = "nombre")
    @NotEmpty
    private String url;

    //@OneToMany(mappedBy = "rol")
    //private List<Usuario> usuarios;
}
