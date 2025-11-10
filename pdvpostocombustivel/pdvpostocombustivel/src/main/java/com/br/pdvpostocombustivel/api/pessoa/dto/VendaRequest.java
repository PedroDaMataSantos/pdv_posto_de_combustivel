package com.br.pdvpostocombustivel.api.pessoa.dto;

import com.br.pdvpostocombustivel.enums.TipoVenda;

import java.math.BigDecimal;
import java.util.List;

public record VendaRequest(
        TipoVenda tipoVenda,
        List<ItemVendaRequest> itens
) {}

