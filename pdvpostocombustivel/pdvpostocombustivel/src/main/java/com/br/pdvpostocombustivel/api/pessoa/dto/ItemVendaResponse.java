package com.br.pdvpostocombustivel.api.pessoa.dto;

import java.math.BigDecimal;

public record ItemVendaResponse(
        String produtoNome,
        BigDecimal quantidade,
        BigDecimal valorUnitario,
        BigDecimal subtotal
) {}
