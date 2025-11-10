package com.br.pdvpostocombustivel.api.pessoa.dto;

import java.util.Date;

public record BombaResponse(
        Long id,
        String numeroBomba,
        Long idEstoque,
        String nomeProduto,
        Long idPreco,
        Date dataCriacao
) {}
