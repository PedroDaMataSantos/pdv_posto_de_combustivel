package com.br.pdvpostocombustivel.api.pessoa.dto;

import com.br.pdvpostocombustivel.enums.TipoVenda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VendaResponse(
        Long id,
        TipoVenda tipoVenda,
        LocalDateTime dataHora,
        BigDecimal valorTotal,
        List<ItemVendaResponse> itens
) {}

