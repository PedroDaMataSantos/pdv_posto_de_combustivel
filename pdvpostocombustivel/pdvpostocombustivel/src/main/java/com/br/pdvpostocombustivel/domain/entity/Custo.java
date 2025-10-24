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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "custo")
@Data
@NoArgsConstructor
public class Custo {

    // atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double imposto;


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

    public Custo(Double imposto,  Double custoVariavel,Double custoFixo, Double margemLucro,
            Date dataProcessamento) {
        this.imposto = imposto;
        this.custoVariavel = custoVariavel;
        this.custoFixo = custoFixo;
        this.margemLucro = margemLucro;
        this.dataProcessamento = dataProcessamento;
    }


}
