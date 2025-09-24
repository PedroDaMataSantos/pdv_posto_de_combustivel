package com.br.pdvpostocombustivel.domain.repository;
import java.util.Optional;
import com.br.pdvpostocombustivel.domain.entity.Preco;

public interface PrecoRepository {
    
    Optional<Preco>findByDataAlteracao(String dataAlteracao);

    boolean existsByDataAlteracao(String dataAlteracao);

}
