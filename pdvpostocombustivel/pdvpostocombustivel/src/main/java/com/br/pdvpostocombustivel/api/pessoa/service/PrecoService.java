package com.br.pdvpostocombustivel.api.pessoa.service;

import com.br.pdvpostocombustivel.api.pessoa.dto.PrecoRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.PrecoResponse;
import com.br.pdvpostocombustivel.domain.entity.Preco;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.PrecoRepository;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import com.br.pdvpostocombustivel.exceptions.PrecoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrecoService {

    private final PrecoRepository precoRepository;
    private final ProdutoRepository produtoRepository;

    public PrecoService(PrecoRepository precoRepository, ProdutoRepository produtoRepository) {
        this.precoRepository = precoRepository;
        this.produtoRepository = produtoRepository;
    }

    public PrecoResponse create(PrecoRequest req) {
        Produto produto = produtoRepository.findById(req.idProduto())
                .orElseThrow(() -> new PrecoException("Produto não encontrado para o ID: " + req.idProduto()));

        Preco preco = new Preco(
                req.valor(),
                req.dataAlteracao(),
                req.horaAlteracao() != null ? req.horaAlteracao() : new Date(),
                produto
        );

        precoRepository.save(preco);
        return toResponse(preco);
    }

    public PrecoResponse getById(Long id) {
        Preco preco = precoRepository.findById(id)
                .orElseThrow(() -> new PrecoException("Preço não encontrado"));
        return toResponse(preco);
    }

    public List<PrecoResponse> listAll() {
        return precoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PrecoResponse update(Long id, PrecoRequest req) {
        Preco preco = precoRepository.findById(id)
                .orElseThrow(() -> new PrecoException("Preço não encontrado"));

        Produto produto = produtoRepository.findById(req.idProduto())
                .orElseThrow(() -> new PrecoException("Produto não encontrado para o ID: " + req.idProduto()));

        preco.setValor(req.valor());
        preco.setDataAlteracao(req.dataAlteracao());
        preco.setHoraAlteracao(req.horaAlteracao() != null ? req.horaAlteracao() : new Date());
        preco.setProduto(produto);

        precoRepository.save(preco);
        return toResponse(preco);
    }

    public void delete(Long id) {
        if (!precoRepository.existsById(id)) {
            throw new PrecoException("Preço não encontrado");
        }
        precoRepository.deleteById(id);
    }

    // ✅ Busca o preço mais recente ou retorna 0 se não houver
    public PrecoResponse buscarPrecoAtual(Long idProduto) {
        return precoRepository.findTopByProdutoIdOrderByHoraAlteracaoDesc(idProduto)
                .map(this::toResponse)
                .orElseGet(() -> new PrecoResponse(
                        null,
                        idProduto,
                        "Produto sem preço registrado",
                        BigDecimal.ZERO,
                        null,
                        null
                ));
    }

    private PrecoResponse toResponse(Preco p) {
        return new PrecoResponse(
                p.getId(),
                p.getProduto().getId(),
                p.getProduto().getNome(),
                p.getValor(),
                p.getDataAlteracao(),
                p.getHoraAlteracao()
        );
    }
}
