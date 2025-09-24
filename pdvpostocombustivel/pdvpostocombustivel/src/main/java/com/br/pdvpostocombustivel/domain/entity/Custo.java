package com.br.pdvpostocombustivel.domain.entity;

//imports

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "custo")
public class Custo {

    // atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double imposto;

    @Column(nullable = false)
    private Double frete;

    @Column(nullable = false)
    private Double custoFixo;

    @Column(nullable = false)
    private Double custoVariavel;

    @Column(nullable = false)
    private Double margemLucro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataProcessamento;

    // contrutor

    public Custo(Double imposto, Double frete, Double custoFixo, Double custoVariavel, Double margemLucro,
            Date dataProcessamento) {
        this.imposto = imposto;
        this.frete = frete;
        this.custoFixo = custoFixo;
        this.custoVariavel = custoVariavel;
        this.custoVariavel = custoVariavel;
        this.margemLucro = margemLucro;
        this.dataProcessamento = dataProcessamento;
    }

    // getter

    public Double getImposto() {
        return imposto;
    }

    public Double getFrete() {
        return frete;
    }

    public Double getCustoFixo() {
        return custoFixo;
    }

    public Double getCustoVariavel() {
        return custoVariavel;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public Date getDataProcessamento() {
        return dataProcessamento;
    }

    // setter
    public void setImposto(Double imposto) {
        this.imposto = imposto;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public void setCustoFixo(Double custoFixo) {
        this.custoFixo = custoFixo;
    }

    public void setCustoVariavel(Double custoVariavel) {
        this.custoVariavel = custoVariavel;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public void setDataProcessamento(Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    // FIM

}
