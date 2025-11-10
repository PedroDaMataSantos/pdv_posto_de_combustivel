package com.br.pdvpostocombustivel.api.pessoa.service;

import com.br.pdvpostocombustivel.api.pessoa.dto.BombaResponse;
import com.br.pdvpostocombustivel.domain.entity.Bomba;
import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.repository.BombaRepository;
import com.br.pdvpostocombustivel.domain.repository.PrecoRepository;
import com.br.pdvpostocombustivel.exceptions.BombaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BombaService {

    private final BombaRepository bombaRepository;
    private final PrecoRepository precoRepository;

    public BombaService(BombaRepository bombaRepository, PrecoRepository precoRepository) {
        this.bombaRepository = bombaRepository;
        this.precoRepository = precoRepository;
    }

    /**
     * Cria uma bomba automaticamente a partir de um estoque recém-criado.
     */
    @Transactional
    public void criarBombaParaEstoque(Estoque estoque) {
        if (estoque == null || estoque.getProduto() == null) {
            throw new BombaException("Estoque ou produto inválido para criação da bomba.");
        }

        // Evita duplicidade
        if (bombaRepository.existsByEstoqueId(estoque.getId())) {
            throw new BombaException("Já existe uma bomba vinculada a este estoque.");
        }

        // Busca o preço atual do produto, se existir
        var precoOpt = precoRepository.findTopByProdutoIdOrderByHoraAlteracaoDesc(estoque.getProduto().getId());

        Bomba bomba = new Bomba();
        bomba.setNumeroBomba("BOMBA-" + estoque.getNumeroBomba());
        bomba.setEstoque(estoque);
        bomba.setPreco(precoOpt.orElse(null));
        bomba.setDataCriacao(new Date());

        bombaRepository.save(bomba);
    }

    /**
     * Retorna todas as bombas registradas.
     */
    public List<BombaResponse> listarTodas() {
        return bombaRepository.findAll().stream()
                .map(b -> new BombaResponse(
                        b.getId(),
                        b.getNumeroBomba(),
                        b.getEstoque() != null ? b.getEstoque().getId() : null,
                        b.getEstoque() != null && b.getEstoque().getProduto() != null
                                ? b.getEstoque().getProduto().getNome()
                                : null,
                        b.getPreco() != null ? b.getPreco().getId() : null,
                        b.getDataCriacao()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Busca uma bomba pelo ID.
     */
    public BombaResponse buscarPorId(Long id) {
        var bomba = bombaRepository.findById(id)
                .orElseThrow(() -> new BombaException("Bomba não encontrada."));

        return new BombaResponse(
                bomba.getId(),
                bomba.getNumeroBomba(),
                bomba.getEstoque() != null ? bomba.getEstoque().getId() : null,
                bomba.getEstoque() != null && bomba.getEstoque().getProduto() != null
                        ? bomba.getEstoque().getProduto().getNome()
                        : null,
                bomba.getPreco() != null ? bomba.getPreco().getId() : null,
                bomba.getDataCriacao()
        );
    }
}
