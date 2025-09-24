package com.br.pdvpostocombustivel.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Produto {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 100,nullable = false)
    private String nome;

    @Column(length = 100,nullable = false)
    private String fornecedor;

    @Column(length = 50,nullable = false)
    private String categoria;
    
    @Column(length = 100,nullable = false)
    private String marca;

    //construtor
    public Produto(String nome, String fornecedor, String categoria, String marca) {
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
        this.marca = marca;
    }
    //getters
    public String getNome() {
        return nome;
    }
    public String getFornecedor() {
        return fornecedor;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getMarca() {
        return marca;
    }
    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    //fim
}
