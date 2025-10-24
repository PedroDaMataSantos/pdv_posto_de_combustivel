package com.br.pdvpostocombustivel.domain.entity;


import com.br.pdvpostocombustivel.enums.TipoProduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 30, nullable = false)
    private String nome;

    @Column(length = 200, nullable = false)
    private String referencia;

    @Column(length = 200, nullable = false)
    private String fornecedor;

    @Column(length = 50, nullable = false)
    private String categoria;

    @Column(length = 50, nullable = false)
    private String marca;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_combustivel", nullable = false)

    private TipoProduto tipo;

    public Produto (String nome, String referencia, String fornecedor, String categoria, String marca, TipoProduto tipo) {
        this.nome = nome;
        this.referencia = referencia;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
        this.marca = marca;
        this.tipo = tipo;
    }

}