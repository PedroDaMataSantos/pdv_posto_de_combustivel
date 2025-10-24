package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contato")
@Data
@NoArgsConstructor
public class Contato {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15, nullable = false)
    private String telefone;

    @Column(length = 50, nullable = false)
    private String email;
    
    @Column(length = 100, nullable = false)
    private String endereco;

    //construtor
    public Contato(String telefone, String email, String endereco) {
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

}
