package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "preco")
@Data
@NoArgsConstructor
public class Preco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(length = 10, nullable = false)
    private String dataAlteracao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date horaAlteracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    public Preco(BigDecimal valor, String dataAlteracao, Date horaAlteracao, Produto produto) {
        this.valor = valor;
        this.dataAlteracao = dataAlteracao;
        this.horaAlteracao = horaAlteracao;
        this.produto = produto;
    }
}
