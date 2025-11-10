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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrecoService {

    private final PrecoRepository repository;
    private final ProdutoRepository produtoRepository;

    public PrecoService(PrecoRepository repository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    public PrecoResponse create(PrecoRequest req) {
        Produto produto = produtoRepository.findById(req.idProduto())
                .orElseThrow(() -> new PrecoException("Produto não encontrado"));

        Preco preco = new Preco(
                req.valor(),
                req.dataAlteracao(),
                req.horaAlteracao() != null ? req.horaAlteracao() : new Date(),
                produto
        );

        repository.save(preco);
        return toResponse(preco);
    }

    public PrecoResponse getById(Long id) {
        Preco preco = repository.findById(id)
                .orElseThrow(() -> new PrecoException("Preço não encontrado"));
        return toResponse(preco);
    }

    public List<PrecoResponse> listAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PrecoResponse update(Long id, PrecoRequest req) {
        Preco preco = repository.findById(id)
                .orElseThrow(() -> new PrecoException("Preço não encontrado"));

        Produto produto = produtoRepository.findById(req.idProduto())
                .orElseThrow(() -> new PrecoException("Produto não encontrado"));

        preco.setValor(req.valor());
        preco.setDataAlteracao(req.dataAlteracao());
        preco.setHoraAlteracao(req.horaAlteracao() != null ? req.horaAlteracao() : new Date());
        preco.setProduto(produto);

        repository.save(preco);
        return toResponse(preco);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new PrecoException("Preço não encontrado");
        }
        repository.deleteById(id);
    }

    private PrecoResponse toResponse(Preco p) {
        return new PrecoResponse(
                p.getId(),
                p.getValor(),
                p.getDataAlteracao(),
                p.getHoraAlteracao(),
                p.getProduto().getId()
        );
    }
}
