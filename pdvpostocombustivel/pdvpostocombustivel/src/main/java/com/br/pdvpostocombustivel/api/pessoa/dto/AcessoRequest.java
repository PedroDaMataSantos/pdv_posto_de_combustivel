package com.br.pdvpostocombustivel.api.pessoa.dto;


import com.br.pdvpostocombustivel.enums.TipoAcesso;

public record AcessoRequest(
        String usuiario,
        String senha,
        TipoAcesso perfil
)
{}
