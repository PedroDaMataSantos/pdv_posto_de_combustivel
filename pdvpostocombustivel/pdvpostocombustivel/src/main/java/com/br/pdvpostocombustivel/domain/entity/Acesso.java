package com.br.pdvpostocombustivel.domain.entity;

import com.br.pdvpostocombustivel.enums.TipoAcesso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="acesso")
@Data
@NoArgsConstructor
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

    public Acesso(String usuario, String senha, TipoAcesso perfil) {
        this.usuario = usuario;
        this.senha = senha;
        this.perfil = perfil;
    }
}