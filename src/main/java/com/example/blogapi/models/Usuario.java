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
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "usuarios")
@ToString @EqualsAndHashCode
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @NotEmpty @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apeliido_materno")
    private String apellidoMaterno;

    @NotEmpty @Getter @Setter @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Getter @Setter @Column(name = "email")
    @NotEmpty private String email;

    @Getter @Setter @Column(name = "sexo")
    private String sexo;

    @NotEmpty @Getter @Setter @Column(name = "password")
    private String password;

    @NotEmpty @Getter @Setter @Column(name = "rol")
    private String rol;

    @NotEmpty @Getter @Setter @Column(name = "estado")
    private String estado;

    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private List<Post> posts;

}
