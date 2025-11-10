package com.br.pdvpostocombustivel.api.pessoa.dto;

import com.br.pdvpostocombustivel.enums.TipoEstoque;

import java.math.BigDecimal;
import java.util.Date;

public record EstoqueResponse(
        Long id,
        Integer numeroBomba,
        BigDecimal quantidade,
        String localTanque,
        String loteEndereco,
        String loteFabricacao,
        Date dataValidade,
        TipoEstoque tipo,
        Long idProduto,
        String nomeProduto
) {}
