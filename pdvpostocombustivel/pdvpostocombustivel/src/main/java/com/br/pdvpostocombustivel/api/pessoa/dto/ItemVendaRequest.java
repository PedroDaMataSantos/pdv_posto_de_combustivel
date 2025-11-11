
package com.br.pdvpostocombustivel.api.pessoa.dto;

import java.math.BigDecimal;

public record ItemVendaRequest(
        Long idProduto,
        BigDecimal quantidade,
        BigDecimal valorUnitario,
        BigDecimal subtotal
) {}