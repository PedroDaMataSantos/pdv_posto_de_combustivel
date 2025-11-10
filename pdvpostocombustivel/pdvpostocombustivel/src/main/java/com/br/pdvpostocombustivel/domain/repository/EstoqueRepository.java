package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByProdutoId(Long produtoId);

    Optional<Estoque> findByNumeroBomba(Integer numeroBomba);

    Optional<Estoque> findByLoteEndereco(String loteEndereco);

    Optional<Estoque> findByLoteFabricacao(String loteFabricacao);

    boolean existsByNumeroBomba(Integer numeroBomba);

    boolean existsByProdutoId(Long produtoId);

    boolean existsByLoteEndereco(String loteEndereco);

    boolean existsByLoteFabricacao(String loteFabricacao);
}
