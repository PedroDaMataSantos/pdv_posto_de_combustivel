package com.br.pdvpostocombustivel.api.pessoa.dto;

import java.math.BigDecimal;

public record ItemVendaRequest(
        Long produtoId,
        BigDecimal quantidade,
        BigDecimal valorUnitario
) {}
