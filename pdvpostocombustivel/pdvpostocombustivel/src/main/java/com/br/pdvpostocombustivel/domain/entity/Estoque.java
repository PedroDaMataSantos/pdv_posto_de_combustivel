package com.br.pdvpostocombustivel.domain.entity;

//imports
import java.math.BigDecimal;
import java.util.Date;

import com.br.pdvpostocombustivel.enums.TipoEstoque;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estoque")
@Data
@NoArgsConstructor
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @Column(length = 200, nullable = false)
    private String localTanque;

    @Column(length = 200, nullable = false)
    private String loteEndereco;

    @Column(length = 200, nullable = false)
    private String loteFabricacao;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataValidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_estoque", nullable = false)
    private TipoEstoque tipo;

    public Estoque (BigDecimal quantidade, String localTanque, String loteEndereco, String loteFabricacao, Date dataValidade, TipoEstoque tipo) {
        this.quantidade = quantidade;
        this.localTanque = localTanque;
        this.loteEndereco = loteEndereco;
        this.loteFabricacao = loteFabricacao;
        this.dataValidade = dataValidade;
        this.tipo = tipo;
    }

}