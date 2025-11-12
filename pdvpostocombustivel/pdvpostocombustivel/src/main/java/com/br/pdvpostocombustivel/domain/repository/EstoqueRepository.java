package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByProdutoId(Long produtoId);

    // 🔹 Retorna o maior número de bomba existente
    @Query("SELECT COALESCE(MAX(e.numeroBomba), 0) FROM Estoque e")
    Optional<Integer> findMaxNumeroBomba();

    // 🔹 Busca estoque pelo número da bomba (para validar duplicidade)
    Optional<Estoque> findByNumeroBomba(Integer numeroBomba);

    Optional<Estoque> findByLoteEndereco(String loteEndereco);

    Optional<Estoque> findByLoteFabricacao(String loteFabricacao);

    boolean existsByProdutoId(Long produtoId);

    boolean existsByLoteEndereco(String loteEndereco);

    boolean existsByLoteFabricacao(String loteFabricacao);
}
