package com.br.pdvpostocombustivel.api.pessoa.service;

import com.br.pdvpostocombustivel.api.pessoa.dto.BombaResponse;
import com.br.pdvpostocombustivel.domain.entity.Bomba;
import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.entity.Preco;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.BombaRepository;
import com.br.pdvpostocombustivel.domain.repository.PrecoRepository;
import com.br.pdvpostocombustivel.exceptions.BombaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelo gerenciamento das bombas de combustível,
 * incluindo criação automática vinculada ao estoque e consulta de dados.
 */
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
     *
     * @param estoque entidade de estoque vinculada ao produto.
     */
    @Transactional
    public void criarBombaParaEstoque(Estoque estoque) {
        if (estoque == null || estoque.getProduto() == null) {
            throw new BombaException("Estoque ou produto inválido para criação da bomba.");
        }

        // Evita duplicidade de bombas para o mesmo estoque
        if (bombaRepository.existsByEstoqueId(estoque.getId())) {
            throw new BombaException("Já existe uma bomba vinculada a este estoque.");
        }

        // Busca o preço mais recente do produto, se existir
        var precoOpt = precoRepository.findTopByProdutoIdOrderByHoraAlteracaoDesc(
                estoque.getProduto().getId()
        );

        Bomba bomba = new Bomba();
        bomba.setNumeroBomba("BOMBA-" + estoque.getNumeroBomba());
        bomba.setEstoque(estoque);
        bomba.setPreco(precoOpt.orElse(null));
        bomba.setDataCriacao(new Date());

        bombaRepository.save(bomba);
    }

    /**
     * Lista todas as bombas cadastradas no sistema,
     * com produto, estoque e valor de preço atual.
     *
     * @return lista de {@link BombaResponse}.
     */
    @Transactional(readOnly = true)
    public List<BombaResponse> listarTodas() {
        return bombaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Busca uma bomba específica pelo seu ID.
     *
     * @param id identificador da bomba.
     * @return {@link BombaResponse} correspondente.
     */
    @Transactional(readOnly = true)
    public BombaResponse buscarPorId(Long id) {
        Bomba bomba = bombaRepository.findById(id)
                .orElseThrow(() -> new BombaException("Bomba não encontrada."));
        return toResponse(bomba);
    }

    /**
     * Converte uma entidade {@link Bomba} para {@link BombaResponse}.
     *
     * @param bomba entidade original.
     * @return DTO com os dados prontos para resposta.
     */
    private BombaResponse toResponse(Bomba bomba) {
        Estoque estoque = bomba.getEstoque();
        Produto produto = estoque != null ? estoque.getProduto() : null;
        Preco preco = bomba.getPreco();

        return new BombaResponse(
                bomba.getId(),
                bomba.getNumeroBomba(),
                estoque != null ? estoque.getId() : null,
                produto != null ? produto.getNome() : null,
                preco != null ? preco.getId() : null,
                preco != null ? preco.getValor() : null,
                bomba.getDataCriacao()
        );
    }
}
