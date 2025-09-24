package com.br.pdvpostocombustivel.domain.repository;
import java.util.Optional;
import com.br.pdvpostocombustivel.domain.entity.Produto;

public interface ProdutoRepository {

    Optional<Produto> findByNome(String nome);
    Optional<Produto>findByMarca(String marca);
    Optional<Produto>findByFornecedor(String fornecedor);
    Optional<Produto>findByCategoria(String categoria);

    boolean existsByNome(String nome);
    boolean existsByMarca(String marca);
    boolean existsByFornecedor(String fornecedor);
    boolean existsByCategoria(String categoria);
    

}
