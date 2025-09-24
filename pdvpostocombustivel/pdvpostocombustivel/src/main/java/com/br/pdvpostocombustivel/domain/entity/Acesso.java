package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="acesso")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //atributos

    @Column(length = 50,nullable = false)
    private String usuario;
    
    @Column(length = 10,nullable = false)
    private String senha;

    //construtor 

    public Acesso(String usuario,String senha){
        this.usuario=usuario;
        this.senha=senha;
    }

    //getters

    public String getUsuario(){
        return usuario;

    }
    public String senha(){
        return senha;

    }

    //setters

    public void setUsuario(String usuario){
        this.usuario=usuario;
    }
    public void setSenha(String senha){
        this.senha=senha;
    }




}
