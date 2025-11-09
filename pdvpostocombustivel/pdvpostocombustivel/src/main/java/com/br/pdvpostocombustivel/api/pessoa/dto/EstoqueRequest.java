package com.br.pdvpostocombustivel.api.pessoa.dto;

import com.br.pdvpostocombustivel.enums.TipoEstoque;
import com.br.pdvpostocombustivel.enums.TipoProduto;

import java.math.BigDecimal;
import java.util.Date;

public record EstoqueRequest(
        BigDecimal quantidade,
        String localTanque,
        String loteEndereco,
        String loteFabricacao,
        Date dataValidade,
        Long idProduto,
        TipoProduto tipoCombustivel

) {
}
