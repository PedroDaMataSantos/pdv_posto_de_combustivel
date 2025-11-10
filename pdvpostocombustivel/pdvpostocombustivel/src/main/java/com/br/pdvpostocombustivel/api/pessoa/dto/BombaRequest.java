package com.br.pdvpostocombustivel.api.pessoa.dto;

public record BombaRequest(
        String numeroBomba,
        Long idEstoque,
        Long idPreco
) {}
