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

        if(repository.existsByusuario(req.usuario())){
            throw new RuntimeException("Usuário já existe: " + req.usuario());
        }


        Acesso novoAcesso = new Acesso(req.usuario(), req.senha(), req.perfil());
        repository.save(novoAcesso);
        return new AcessoResponse(novoAcesso.getId(), novoAcesso.getUsuario(), novoAcesso.getPerfil());
    }
}