package com.br.pdvpostocombustivel.api.pessoa.dto;

import java.math.BigDecimal;
import java.util.Date;

public record EstoqueResponse(
        Long id,
        BigDecimal quantidade,
        String localTanque,
        String loteEndereco,
        String loteFabricacao,
        Date dataValidade
) {
}
