package com.br.pdvpostocombustivel.api.pessoa.service;

import com.br.pdvpostocombustivel.api.pessoa.dto.CustoRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.CustoResponse;
import com.br.pdvpostocombustivel.domain.entity.Custo;
import com.br.pdvpostocombustivel.domain.repository.CustoRepository;
import com.br.pdvpostocombustivel.exceptions.CustoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustoService {

    private final CustoRepository repository;

    public CustoService(CustoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustoResponse create(CustoRequest req) {
        double aliquota = calcularAliquota(req.margemLucro());

        Custo custo = new Custo(
                aliquota,
                req.custoVariavel(),
                req.custoFixo(),
                req.margemLucro(),
                req.dataProcessamento()
        );

        repository.save(custo);
        return toResponse(custo);
    }

    public CustoResponse getById(Long id) {
        Custo custo = repository.findById(id)
                .orElseThrow(() -> new CustoException("Custo não encontrado"));
        return toResponse(custo);
    }

    public List<CustoResponse> listAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CustoResponse update(Long id, CustoRequest req) {
        Custo custo = repository.findById(id)
                .orElseThrow(() -> new CustoException("Custo não encontrado"));

        double aliquota = calcularAliquota(req.margemLucro());

        custo.setImposto(aliquota);
        custo.setCustoVariavel(req.custoVariavel());
        custo.setCustoFixo(req.custoFixo());
        custo.setMargemLucro(req.margemLucro());
        custo.setDataProcessamento(req.dataProcessamento());

        repository.save(custo);
        return toResponse(custo);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new CustoException("Custo não encontrado");
        }
        repository.deleteById(id);
    }

    private CustoResponse toResponse(Custo custo) {
        return new CustoResponse(
                custo.getId(),
                custo.getImposto(),
                custo.getCustoVariavel(),
                custo.getCustoFixo(),
                custo.getMargemLucro(),
                custo.getDataProcessamento()
        );
    }

    private double calcularAliquota(Double margemLucro) {
        if (margemLucro == null || margemLucro <= 0) {
            return 0.0; // sem incidência
        }
        return 0.15; // 15% fixos (CSSL)
    }
}
