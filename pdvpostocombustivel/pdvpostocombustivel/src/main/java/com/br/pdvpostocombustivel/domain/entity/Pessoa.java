package com.br.pdvpostocombustivel.domain.entity;

import java.util.Date;

public class Pessoa {
    private String nomeCompleto;
    private String cpfCnpj;
    private Long numeroCtps;
    private Date dataNascimento;

    public Pessoa(String nomeCompleto, String cpfCnpj, Long numeroCtps, Date dataNascimento) {
        super();
        this.numeroCtps = numeroCtps;
        this.dataNascimento = dataNascimento;
        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cpfCnpj;
    }

    public Pessoa() {

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
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}

