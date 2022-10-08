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
import javax.validation.constraints.NotNull;
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

    @NotEmpty @Getter @Setter @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Getter @Setter @Column(name = "email")
    @NotEmpty private String email;

    @Getter @Setter @Column(name = "sexo")
    private String sexo;

    @NotEmpty @Getter @Setter @Column(name = "password")
    private String password;

    @NotNull @Getter @Setter @Column(name = "id_rol")
    private Integer idRol;

    @NotEmpty @Getter @Setter @Column(name = "estado")
    private String estado;

    @Getter @Setter @Column(name = "descripcion")
    private String descripcion;

    @NotEmpty @Getter @Setter @Column(name = "fecha_creacion")
    private String fechaCreacion;

    @Getter @Setter @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    //@ManyToOne
    //@Getter @Setter @JoinColumn(name = "id_rol")
    //private Rol rol;

    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private List<Post> posts;

    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private List<Comentario> comentarios;

}
