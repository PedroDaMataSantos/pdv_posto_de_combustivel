package com.br.pdvpostocombustivel.api.pessoa.dto;

public record CustoResponse(
    Double imposto,
    Double frete,
    Double custoFixo,
    Double custoVariavel,
    Double margemLucro,
    String dataProcessamento
) {

}
