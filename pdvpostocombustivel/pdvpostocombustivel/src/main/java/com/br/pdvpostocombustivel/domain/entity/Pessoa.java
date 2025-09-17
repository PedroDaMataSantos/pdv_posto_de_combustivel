package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String nomeCompleto;

    @Column(length = 14, nullable = false)
    private String cpfCnpj;

    @Column(length = 12)
    private Long numeroCtps;

    @Column(length = 10, nullable = false)
    private LocalDate dataNascimento;

    public Pessoa(String nomeCompleto,
                  String cpfCnpj,
                  Long numeroCtps,
                  LocalDate dataNascimento) {
        this.numeroCtps = numeroCtps;
        this.dataNascimento = dataNascimento;
        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cpfCnpj;
    }

    public Pessoa() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public String getCpfCnpj() {
        return cpfCnpj;
    }
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
    public Long getNumeroCtps() {
        return numeroCtps;
    }
    public void setNumeroCtps(Long numeroCtps) {
        this.numeroCtps = numeroCtps;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}

