package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "item_venda")
@Data
@NoArgsConstructor
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda", nullable = false)
    private Venda venda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal quantidade;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    public ItemVenda(Produto produto, BigDecimal quantidade, BigDecimal valorUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.subtotal = quantidade.multiply(valorUnitario);
    }
}
