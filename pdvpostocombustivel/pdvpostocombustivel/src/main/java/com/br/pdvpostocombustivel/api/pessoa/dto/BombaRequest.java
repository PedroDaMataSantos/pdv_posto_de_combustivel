package com.br.pdvpostocombustivel.api.pessoa.dto;


import java.util.Date;

public record BombaRequest(
        String numeroBomba,
        Long idEstoque,
        Long idPreco,
        Date dataCriacao
) {}