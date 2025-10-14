package com.br.pdvpostocombustivel.api.pessoa.service;

import com.br.pdvpostocombustivel.api.pessoa.dto.AcessoRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.AcessoResponse;
import com.br.pdvpostocombustivel.domain.entity.Acesso;
import com.br.pdvpostocombustivel.domain.repository.AcessoRepository;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {

    private final AcessoRepository repository;

    public AcessoService(AcessoRepository repository) {
        this.repository = repository;
    }

    public AcessoResponse registrar(AcessoRequest req) {
        // A senha da requisição é salva diretamente, sem criptografar
        Acesso novoAcesso = new Acesso(req.usuiario(), req.senha());

        repository.save(novoAcesso);
        return new AcessoResponse(novoAcesso.getId(), novoAcesso.getUsuario());
    }
}
