package com.br.pdvpostocombustivel.api.pessoa.dto;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record CustoRequest(
        Double imposto,
        Double custoVariavel,
        Double custoFixo,
        Double margemLucro,
        Date dataProcessameto
) {
}