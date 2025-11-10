package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.Preco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PrecoRepository extends JpaRepository<Preco, Long> {

    @Query("""
        SELECT p FROM Preco p
        WHERE p.produto.id = :idProduto
        ORDER BY p.horaAlteracao DESC
        LIMIT 1
    """)
    Optional<Preco> findPrecoAtual(Long idProduto);
}
