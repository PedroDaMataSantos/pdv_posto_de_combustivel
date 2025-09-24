package com.br.pdvpostocombustivel.domain.repository;
import java.util.Optional;
import com.br.pdvpostocombustivel.domain.entity.Contato;

public interface ContatoRepository {

    Optional<Contato>findByEmail(String email);
    Optional<Contato>findByTelefone(Long telefone);

    boolean existsByEmail(String email);
    boolean existsByTelefone(Long telefone);



}
