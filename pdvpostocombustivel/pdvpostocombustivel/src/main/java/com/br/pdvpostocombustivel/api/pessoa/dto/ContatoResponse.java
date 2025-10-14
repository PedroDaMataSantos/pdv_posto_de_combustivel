package com.br.pdvpostocombustivel.api.pessoa.dto;

public record ContatoResponse(
        Long id,
        String telefone,
        String email,
        String endereco
) {
}
