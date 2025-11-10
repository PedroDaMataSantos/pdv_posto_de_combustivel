package com.br.pdvpostocombustivel.api.pessoa.service;

import com.br.pdvpostocombustivel.api.pessoa.dto.EstoqueRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.EstoqueResponse;
import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.EstoqueRepository;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import com.br.pdvpostocombustivel.enums.TipoEstoque;
import com.br.pdvpostocombustivel.exceptions.EstoqueException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;
    private final ProdutoRepository produtoRepository;
    private final BombaService bombaService; // ✅ nova dependência

    public EstoqueService(
            EstoqueRepository repository,
            ProdutoRepository produtoRepository,
            BombaService bombaService
    ) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
        this.bombaService = bombaService;
    }

    @Transactional
    public EstoqueResponse create(EstoqueRequest req) {
        validarQuantidade(req.quantidade());
        validarNumeroBombaUnico(req.numeroBomba());

        Produto produto = produtoRepository.findById(req.idProduto())
                .orElseThrow(() -> new EstoqueException("Produto não encontrado."));

        TipoEstoque tipoCalculado = Estoque.calcularTipo(req.quantidade());

        Estoque estoque = new Estoque(
                req.numeroBomba(),
                req.quantidade(),
                req.localTanque(),
                req.loteEndereco(),
                req.loteFabricacao(),
                req.dataValidade(),
                tipoCalculado,
                produto
        );

        repository.save(estoque);

        // ✅ Cria automaticamente a bomba para este estoque
        try {
            bombaService.criarBombaParaEstoque(estoque);
        } catch (Exception e) {
            throw new EstoqueException("Erro ao criar bomba vinculada ao estoque: " + e.getMessage());
        }

        return toResponse(estoque);
    }

    @Transactional
    public EstoqueResponse update(Long id, EstoqueRequest req) {
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new EstoqueException("Estoque não encontrado."));

        validarQuantidade(req.quantidade());

        Produto produto = produtoRepository.findById(req.idProduto())
                .orElseThrow(() -> new EstoqueException("Produto não encontrado."));

        if (!estoque.getNumeroBomba().equals(req.numeroBomba())) {
            validarNumeroBombaUnico(req.numeroBomba());
        }

        TipoEstoque tipoCalculado = Estoque.calcularTipo(req.quantidade());

        estoque.setNumeroBomba(req.numeroBomba());
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
            throw new EstoqueException("Estoque não encontrado.");
        }
        repository.deleteById(id);
    }

    public EstoqueResponse getById(Long id) {
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new EstoqueException("Estoque não encontrado."));
        return toResponse(estoque);
    }

    public List<EstoqueResponse> listAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private void validarNumeroBombaUnico(Integer numeroBomba) {
        if (numeroBomba == null)
            throw new EstoqueException("Número da bomba não pode ser nulo.");
        repository.findByNumeroBomba(numeroBomba).ifPresent(e -> {
            throw new EstoqueException("Já existe uma bomba registrada com o número " + numeroBomba);
        });
    }

    private void validarQuantidade(BigDecimal quantidade) {
        if (quantidade == null)
            throw new EstoqueException("Quantidade não pode ser nula.");
        if (quantidade.compareTo(BigDecimal.ZERO) < 0)
            throw new EstoqueException("Quantidade não pode ser negativa.");
        if (quantidade.compareTo(Estoque.LIMITE_TANQUE) > 0)
            throw new EstoqueException("Quantidade não pode ultrapassar o limite do tanque de " + Estoque.LIMITE_TANQUE + " litros.");
    }

    private EstoqueResponse toResponse(Estoque estoque) {
        return new EstoqueResponse(
                estoque.getId(),
                estoque.getNumeroBomba(),
                estoque.getQuantidade(),
                estoque.getLocalTanque(),
                estoque.getLoteEndereco(),
                estoque.getLoteFabricacao(),
                estoque.getDataValidade(),
                estoque.getTipo(),
                estoque.getProduto().getId(),
                estoque.getProduto().getNome()
        );
    }
}
