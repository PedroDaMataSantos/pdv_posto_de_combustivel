package com.br.pdvpostocombustivel.api.pessoa.service;

import com.br.pdvpostocombustivel.api.pessoa.dto.EstoqueRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.EstoqueResponse;
import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.EstoqueRepository;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import com.br.pdvpostocombustivel.enums.TipoEstoque;
import com.br.pdvpostocombustivel.exceptions.EstoqueException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;
    private final ProdutoRepository produtoRepository;
    private final BombaService bombaService;

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

        Produto produto = produtoRepository.findById(req.idProduto())
                .orElseThrow(() -> new EstoqueException("Produto não encontrado."));

        TipoEstoque tipoCalculado = Estoque.calcularTipo(req.quantidade());

        Estoque estoque = new Estoque();
        estoque.setQuantidade(req.quantidade());
        estoque.setLocalTanque(req.localTanque());
        estoque.setLoteEndereco(req.loteEndereco());
        estoque.setLoteFabricacao(req.loteFabricacao());
        estoque.setDataValidade(req.dataValidade());
        estoque.setTipo(tipoCalculado);
        estoque.setProduto(produto);

        // 🔹 Número da bomba: usa o informado ou gera automaticamente
        Integer numeroBomba;
        if (req.numeroBomba() != null) {
            numeroBomba = req.numeroBomba();
            validarNumeroBombaUnico(numeroBomba);
        } else {
            numeroBomba = repository.findMaxNumeroBomba().orElse(0) + 1;
        }

        estoque.setNumeroBomba(numeroBomba);

        repository.save(estoque);

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

        TipoEstoque tipoCalculado = Estoque.calcularTipo(req.quantidade());

        estoque.setQuantidade(req.quantidade());
        estoque.setLocalTanque(req.localTanque());
        estoque.setLoteEndereco(req.loteEndereco());
        estoque.setLoteFabricacao(req.loteFabricacao());
        estoque.setDataValidade(req.dataValidade());
        estoque.setTipo(tipoCalculado);
        estoque.setProduto(produto);

        // 🔹 Atualiza número da bomba (somente se alterado)
        if (req.numeroBomba() != null && !req.numeroBomba().equals(estoque.getNumeroBomba())) {
            validarNumeroBombaUnico(req.numeroBomba());
            estoque.setNumeroBomba(req.numeroBomba());
        }

        repository.save(estoque);
        return toResponse(estoque);
    }

    @Transactional
    public void delete(Long id) {
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new EstoqueException("Estoque não encontrado."));

        repository.delete(estoque);
    }

    @Transactional(readOnly = true)
    public EstoqueResponse getById(Long id) {
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new EstoqueException("Estoque não encontrado."));
        return toResponse(estoque);
    }

    @Transactional(readOnly = true)
    public List<EstoqueResponse> listAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private void validarQuantidade(BigDecimal quantidade) {
        if (quantidade == null)
            throw new EstoqueException("Quantidade não pode ser nula.");
        if (quantidade.compareTo(BigDecimal.ZERO) < 0)
            throw new EstoqueException("Quantidade não pode ser negativa.");
        if (quantidade.compareTo(Estoque.LIMITE_TANQUE) > 0)
            throw new EstoqueException("Quantidade não pode ultrapassar o limite do tanque de " + Estoque.LIMITE_TANQUE + " litros.");
    }

    private void validarNumeroBombaUnico(Integer numeroBomba) {
        repository.findByNumeroBomba(numeroBomba).ifPresent(e -> {
            throw new EstoqueException("Já existe um estoque com o número de bomba " + numeroBomba);
        });
    }

    private EstoqueResponse toResponse(Estoque estoque) {
        Produto produto = estoque.getProduto();
        return new EstoqueResponse(
                estoque.getId(),
                estoque.getNumeroBomba(), // ✅ incluído
                estoque.getQuantidade(),
                estoque.getLocalTanque(),
                estoque.getLoteEndereco(),
                estoque.getLoteFabricacao(),
                estoque.getDataValidade(),
                estoque.getTipo(),
                produto != null ? produto.getId() : null,
                produto != null ? produto.getNome() : null
        );
    }
}
