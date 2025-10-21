package com.br.pdvpostocombustivel.api.pessoa.service;

import com.br.pdvpostocombustivel.api.pessoa.dto.EstoqueRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.EstoqueResponse;
import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.repository.EstoqueRepository;
import com.br.pdvpostocombustivel.exceptions.EstoqueException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public EstoqueResponse create(EstoqueRequest req) {
        Estoque estoque = new Estoque(req.quantidade(), req.localTanque(), req.loteEndereco(), req.loteFabricacao(), req.dataValidade(), req.tipo());
        repository.save(estoque);
        return toResponse(estoque);
    }

    public EstoqueResponse getById(Long id) {
        Estoque estoque = repository.findById(id).orElseThrow(() -> new EstoqueException("Estoque não encontrado"));
        return toResponse(estoque);
    }

    public List<EstoqueResponse> listAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public EstoqueResponse update(Long id, EstoqueRequest req) {
        Estoque estoque = repository.findById(id).orElseThrow(() -> new EstoqueException("Estoque não encontrado"));
        estoque.setQuantidade(req.quantidade());
        estoque.setLocalTanque(req.localTanque());
        estoque.setLoteEndereco(req.loteEndereco());
        estoque.setLoteFabricacao(req.loteFabricacao());
        estoque.setDataValidade(req.dataValidade());
        estoque.setTipo(req.tipo());
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
                estoque.getTipo()
        );
    }
}