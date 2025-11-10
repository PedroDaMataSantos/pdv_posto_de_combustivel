package com.br.pdvpostocombustivel.domain.entity;

import com.br.pdvpostocombustivel.enums.TipoEstoque;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Entity
@Table(name = "estoque")
@Data
@NoArgsConstructor
public class Estoque {

    public static final BigDecimal LIMITE_TANQUE = new BigDecimal("60000");

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "numero_bomba", nullable = false, unique = true)
    private Integer numeroBomba;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    public Estoque(Integer numeroBomba, BigDecimal quantidade, String localTanque,
                   String loteEndereco, String loteFabricacao, Date dataValidade,
                   TipoEstoque tipo, Produto produto) {
        this.numeroBomba = numeroBomba;
        this.quantidade = quantidade;
        this.localTanque = localTanque;
        this.loteEndereco = loteEndereco;
        this.loteFabricacao = loteFabricacao;
        this.dataValidade = dataValidade;
        this.tipo = tipo;
        this.produto = produto;
    }

    public static TipoEstoque calcularTipo(BigDecimal quantidade) {
        if (quantidade == null || quantidade.compareTo(BigDecimal.ZERO) == 0)
            return TipoEstoque.INDISPONIVEL;

        BigDecimal percentual = quantidade
                .multiply(new BigDecimal("100"))
                .divide(LIMITE_TANQUE, 2, RoundingMode.HALF_UP);

        if (percentual.compareTo(new BigDecimal("50")) >= 0)
            return TipoEstoque.OK;
        else if (percentual.compareTo(new BigDecimal("25")) >= 0)
            return TipoEstoque.BAIXO;
        else if (percentual.compareTo(BigDecimal.ZERO) > 0)
            return TipoEstoque.CRITICO;
        else
            return TipoEstoque.INDISPONIVEL;
    }

    public BigDecimal calcularPercentual() {
        if (quantidade == null || quantidade.compareTo(BigDecimal.ZERO) == 0)
            return BigDecimal.ZERO;

        return quantidade
                .multiply(new BigDecimal("100"))
                .divide(LIMITE_TANQUE, 2, RoundingMode.HALF_UP);
    }
}
