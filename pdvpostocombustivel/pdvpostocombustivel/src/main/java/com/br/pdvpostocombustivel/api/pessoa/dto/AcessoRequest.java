package com.br.pdvpostocombustivel.api.pessoa.dto;


import com.br.pdvpostocombustivel.enums.TipoAcesso;

public record AcessoRequest(
        String usuario,
        String senha,
        TipoAcesso perfil
)
{}
