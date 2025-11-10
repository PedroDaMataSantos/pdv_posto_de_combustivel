package com.br.pdvpostocombustivel.api.pessoa.dto;

import java.util.Date;

public record CustoResponse(
        Long id,
        Double imposto,
        Double custoVariavel,
        Double custoFixo,
        Double margemLucro,
        Date dataProcessamento
) { }
