package com.br.pdvpostocombustivel.api.pessoa.dto;

import java.util.Date;

public record CustoRequest(
        Double imposto,          // 0.15 ou 0.0
        Double custoVariavel,
        Double custoFixo,
        Double margemLucro,
        Date dataProcessamento   // nome padronizado
) {}