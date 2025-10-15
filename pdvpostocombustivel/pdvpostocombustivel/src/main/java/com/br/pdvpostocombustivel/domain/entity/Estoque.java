package com.br.pdvpostocombustivel.domain.entity;

//imports
import java.math.BigDecimal;
import java.util.Date;

import com.br.pdvpostocombustivel.enums.TipoEstoque;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="estoque")

public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // atributos

    @Column(precision = 12, nullable = false)
    private BigDecimal quantidade;

    @Column(length = 50, nullable = false)
    private String localTanque;

    @Column(length = 50, nullable = false)
    private String localEndereco;

    @Column(length = 50, nullable = false)
    private String loteFabricacao;

    @Column(length = 10, nullable = false)
    private Date dataValidade;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_estoque", nullable = false)
    private TipoEstoque tipo;

    //construtor

    public Estoque(BigDecimal quantidade, String localEndereco, String localTanque, String loteFabricacao, Date dataValidade) {
        this.quantidade = quantidade;
        this.localEndereco = localEndereco;
        this.localTanque = localTanque;
        this.loteFabricacao = loteFabricacao;
        this.dataValidade = dataValidade;
    }

    public Estoque() {

    }


    //getters

    public String getLoteEndereco() {
        return localEndereco;
    }

    public String getLocalTanque() {
        return localTanque;
    }

    public String getLoteFabricacao() {
        return loteFabricacao;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public Long getId() {
        return id;
    }
    public void setTipo(TipoEstoque tipo) {
        this.tipo = tipo;
    }


    //setter
    public void setLoteFabricacao(String loteFabrica) {
        this.loteFabricacao = loteFabrica;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setLocalTanque(String localTanque) {
        this.localTanque = localTanque;
    }

    public void setLoteEndereco(String localEndereco) {
        this.localEndereco = localEndereco;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public TipoEstoque getTipo() {
        return tipo;
    }
}
        

