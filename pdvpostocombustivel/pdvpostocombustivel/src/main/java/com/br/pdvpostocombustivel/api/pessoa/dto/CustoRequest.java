package com.br.pdvpostocombustivel.api.pessoa.dto;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public record CustoRequest(
    Double imposto,
    Double frete,
    Double custoFixo,
    Double custoVariavel,
    Double margemLucro,
    Date dataProcessamento 
    ) {}
