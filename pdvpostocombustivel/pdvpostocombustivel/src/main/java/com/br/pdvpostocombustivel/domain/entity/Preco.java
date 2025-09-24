package com.br.pdvpostocombustivel.domain.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import jakarta.persistence.Temporal;
import java.util.Date;

@Entity
@Table(name="preco")

public class Preco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 7,nullable = false)
    private BigDecimal valor;

    @Column(length = 10,nullable = false)
    private String dataAlteracao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date horaAlteracao;

    // construtor

    public Preco(BigDecimal valor, String dataAlteracao, Date horaAlteracao) {
        this.valor = valor;
        this.dataAlteracao = dataAlteracao;
        this.horaAlteracao = horaAlteracao;
    }
    // getters
    public BigDecimal getValor() {
        return valor;
    }

    public String getDataAlteracao() {
        return dataAlteracao;
    }
    public Date getHoraAlteracao() {
        return horaAlteracao;
    }
    // setters
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public void setHoraAlteracao(Date horaAlteracao) {
        this.horaAlteracao = horaAlteracao;
    }
    // fim
    
    
}
