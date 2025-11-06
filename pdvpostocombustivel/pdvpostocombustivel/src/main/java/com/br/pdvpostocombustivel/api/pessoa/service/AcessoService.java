package com.br.pdvpostocombustivel.api.pessoa.service;

import com.br.pdvpostocombustivel.api.pessoa.dto.AcessoRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.AcessoResponse;
import com.br.pdvpostocombustivel.domain.entity.Acesso;
import com.br.pdvpostocombustivel.domain.repository.AcessoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcessoService {

    private final AcessoRepository repository;

    public AcessoService(AcessoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AcessoResponse registrar(AcessoRequest req) {

        if(repository.existsByusuario(req.usuario())){
            throw new RuntimeException("Usuário já existe: " + req.usuario());
        }

        Acesso novoAcesso = new Acesso(req.usuario(), req.senha(), req.perfil());
        repository.save(novoAcesso);
        return new AcessoResponse(novoAcesso.getId(), novoAcesso.getUsuario(), novoAcesso.getPerfil());
    }

    public List<AcessoResponse> listAll() {
        return repository.findAll().stream()
                .map(a -> new AcessoResponse(a.getId(), a.getUsuario(), a.getPerfil()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Acesso não encontrado");
        }
        repository.deleteById(id);
    }
}