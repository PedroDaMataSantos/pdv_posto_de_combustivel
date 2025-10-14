package com.br.pdvpostocombustivel.api.pessoa.dto;

public record ContatoRequest(
        String telefone,
        String email,
        String endereco
) {
}
