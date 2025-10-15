package com.br.pdvpostocombustivel.domain.entity;

import com.br.pdvpostocombustivel.enums.TipoAcesso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="acesso")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //atributos

    @Column(length = 50, nullable = false)
    private String usuario;

    @Column(length = 10, nullable = false)
    private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAcesso perfil;


    //construtor 

    public Acesso(String usuario, String senha,TipoAcesso perfil) {
        this.usuario = usuario;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Acesso() {

    }



    //getters

    public String getUsuario() {
        return usuario;

    }

    public String senha() {
        return senha;

    }

    public void setPerfil(TipoAcesso perfil) {
        this.perfil = perfil;
    }

    //setters

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public TipoAcesso getPerfil() {
        return perfil;
    }
}
