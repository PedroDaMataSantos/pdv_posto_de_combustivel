package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@Entity
@Table(name = "bomba")
public class Bomba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroBomba;

    @OneToOne
    @JoinColumn(name = "estoque_id", nullable = false)
    private Estoque estoque;

    @ManyToOne
    @JoinColumn(name = "preco_id", nullable = true)
    private Preco preco;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao = new Date();


    public Bomba(String numeroBomba, Estoque estoque, Preco preco) {
        this.numeroBomba = numeroBomba;
        this.estoque = estoque;
        this.preco = preco;
    }


}
