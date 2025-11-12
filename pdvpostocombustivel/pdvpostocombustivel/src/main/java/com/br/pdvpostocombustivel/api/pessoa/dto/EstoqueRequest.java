package com.br.pdvpostocombustivel.api.pessoa.dto;

import com.br.pdvpostocombustivel.enums.TipoEstoque;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public record EstoqueRequest(
        BigDecimal quantidade,
        String localTanque,
        String loteEndereco,
        String loteFabricacao,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        Date dataValidade,
        TipoEstoque tipo,
        Long idProduto,
        Integer numeroBomba
) {}
