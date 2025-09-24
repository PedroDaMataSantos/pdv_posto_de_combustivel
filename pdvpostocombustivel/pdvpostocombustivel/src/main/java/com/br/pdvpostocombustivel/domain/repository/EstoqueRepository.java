package com.br.pdvpostocombustivel.domain.repository;
import java.util.Optional;
import com.br.pdvpostocombustivel.domain.entity.Estoque;

public interface EstoqueRepository {

    Optional<Estoque> findByLocalTanque(String localTanque);
    Optional<Estoque> findByLocalEndereco(String localEndereco);
    Optional<Estoque> findByLoteFabrica(String loteFabrica);
    Optional<Estoque>findByDataValidade(String dataValidade);

    boolean existsByLocalTanque(String localTanque);
    boolean existsByLocalEndereco(String localEndereco);
    boolean existsByLoteFabrica(String loteFabrica); 
    boolean existsByDataValidade(String dataValidade);
    


}
