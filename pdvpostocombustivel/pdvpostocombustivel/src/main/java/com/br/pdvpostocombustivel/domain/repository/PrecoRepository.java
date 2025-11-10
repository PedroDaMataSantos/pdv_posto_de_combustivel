package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.Preco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrecoRepository extends JpaRepository<Preco, Long> {

    // ✅ Remove o findPrecoAtual — o Spring tenta gerar query inválida
    // ✅ Usa apenas o método correto, que o Spring entende e já ordena
    Optional<Preco> findTopByProdutoIdOrderByHoraAlteracaoDesc(Long idProduto);

    boolean existsByProdutoId(Long idProduto);

    boolean existsByProdutoIdAndHoraAlteracao(Long idProduto, java.util.Date horaAlteracao);
}
