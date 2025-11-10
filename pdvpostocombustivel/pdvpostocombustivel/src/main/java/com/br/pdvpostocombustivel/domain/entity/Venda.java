package com.br.pdvpostocombustivel.domain.entity;

import com.br.pdvpostocombustivel.enums.TipoVenda;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venda")
@Data
@NoArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoVenda tipoVenda;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itens = new ArrayList<>();

    public Venda(TipoVenda tipoVenda) {
        this.tipoVenda = tipoVenda;
        this.dataHora = LocalDateTime.now();
        this.valorTotal = BigDecimal.ZERO;
    }

    public void adicionarItem(ItemVenda item) {
        item.setVenda(this);
        this.itens.add(item);
        recalcularTotal();
    }

    public void recalcularTotal() {
        this.valorTotal = itens.stream()
                .map(ItemVenda::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
