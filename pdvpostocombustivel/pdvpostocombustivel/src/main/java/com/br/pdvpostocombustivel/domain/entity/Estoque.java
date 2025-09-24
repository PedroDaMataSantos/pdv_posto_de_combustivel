package com.br.pdvpostocombustivel.domain.entity;

//imports
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="estoque")

public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // atributos

    @Column(precision = 12,nullable = false)
    private BigDecimal quantidade;

    @Column(length = 50,nullable = false)
    private String localTanque;

    @Column(length = 50,nullable = false)
    private String localEndereco;

    @Column(length = 50,nullable = false)
    private String loteFabrica;
    
    @Column(length = 10,nullable = false)
    private String dataValidade;

    //construtor

    public Estoque(String localEndereco,String localTanque,String loteFabrica,BigDecimal quantidade,String dataValidade){
    this.quantidade=quantidade;
    this.localEndereco=localEndereco;
    this.localTanque=localTanque;
    this.loteFabrica=loteFabrica;
    this.dataValidade=dataValidade;
    }

    //metodos

    public void Abastecer(BigDecimal quantidade) {
        
        this.quantidade=this.quantidade.add(quantidade);
        System.out.println("Foi abastecido "+ quantidade+"L. Quantidade atual em estoque " + this.quantidade+"L");
        
    }
    public void Desabastecer(BigDecimal quantidade) {

        this.quantidade=this.quantidade.subtract(quantidade);
           System.out.println("Foi desabastecido "+ quantidade+"L. Quantidade atual em estoque " + this.quantidade+"L");
        
    }

    //getters

    public String getLocalEndereco(){
        return localEndereco;
    }
    public String getLocalTanque(){
        return localTanque;
    }
    public String getLotefabrica(){
        return loteFabrica;
    }
    public BigDecimal getQuantidade(){
        return quantidade;
    }
    public String getDataValidade(){
        return dataValidade;
    }

    //setter
    public void setLoteFabrica(String loteFabrica){
        this.loteFabrica=loteFabrica;
    }
    public void setQuantidade(BigDecimal quantidade){
        this.quantidade=quantidade;
    }
    public void setDataValidade(String dataValidade){
        this.dataValidade=dataValidade;
    }
    public void setLocalTanque(String localTanque){
        this.localTanque=localTanque;
    }
    public void setLocalEndereco(String localEndereco){
        this.localEndereco=localEndereco;





    
    }
    //fim
        
    }

