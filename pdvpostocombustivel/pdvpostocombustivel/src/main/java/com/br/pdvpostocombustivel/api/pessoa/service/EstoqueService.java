package com.br.pdvpostocombustivel.api.pessoa.service;

import com.br.pdvpostocombustivel.api.pessoa.dto.EstoqueRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.EstoqueResponse;
import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.EstoqueRepository;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import com.br.pdvpostocombustivel.exceptions.EstoqueException;
import com.br.pdvpostocombustivel.enums.TipoEstoque;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository repository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public EstoqueResponse create(EstoqueRequest req) {

        validarQuantidade(req.quantidade());

        Produto produto = produtoRepository.findById(req.idProduto())
                .orElseThrow(() -> new EstoqueException("Produto não encontrado"));

        TipoEstoque tipoCalculado = Estoque.calcularTipo(req.quantidade());

        Estoque estoque = new Estoque(
                req.quantidade(),
                req.localTanque(),
                req.loteEndereco(),
                req.loteFabricacao(),
                req.dataValidade(),
                tipoCalculado,
                produto
        );

        repository.save(estoque);
        return toResponse(estoque);
    }

    public EstoqueResponse getById(Long id) {
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new EstoqueException("Estoque não encontrado"));
        return toResponse(estoque);
    }

    public List<EstoqueResponse> listAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public EstoqueResponse update(Long id, EstoqueRequest req) {

        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new EstoqueException("Estoque não encontrado"));

        validarQuantidade(req.quantidade());

        Produto produto = produtoRepository.findById(req.idProduto())
                .orElseThrow(() -> new EstoqueException("Produto não encontrado"));

        TipoEstoque tipoCalculado = Estoque.calcularTipo(req.quantidade());

        estoque.setQuantidade(req.quantidade());
        estoque.setLocalTanque(req.localTanque());
        estoque.setLoteEndereco(req.loteEndereco());
        estoque.setLoteFabricacao(req.loteFabricacao());
        estoque.setDataValidade(req.dataValidade());
        estoque.setTipo(tipoCalculado);
        estoque.setProduto(produto);

        repository.save(estoque);
        return toResponse(estoque);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EstoqueException("Estoque não encontrado");
        }
        repository.deleteById(id);
    }

    private EstoqueResponse toResponse(Estoque estoque) {
        return new EstoqueResponse(
                estoque.getId(),
                estoque.getQuantidade(),
                estoque.getLocalTanque(),
                estoque.getLoteEndereco(),
                estoque.getLoteFabricacao(),
                estoque.getDataValidade(),
                estoque.getTipo(),
                estoque.getProduto().getId()


        );
    }

    private void validarQuantidade(BigDecimal quantidade) {
        if (quantidade == null) {
            throw new EstoqueException("Quantidade não pode ser nula");
        }

        if (quantidade.compareTo(BigDecimal.ZERO) < 0) {
            throw new EstoqueException("Quantidade não pode ser negativa");
        }

        if (quantidade.compareTo(Estoque.LIMITE_TANQUE) > 0) {
            throw new EstoqueException(
                    "Quantidade não pode ultrapassar o limite do tanque de " + Estoque.LIMITE_TANQUE + " litros"
            );
        }
    }
}
