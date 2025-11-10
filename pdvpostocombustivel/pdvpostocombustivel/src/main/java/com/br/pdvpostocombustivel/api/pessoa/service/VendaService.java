package com.br.pdvpostocombustivel.api.pessoa.service;

import com.br.pdvpostocombustivel.api.pessoa.dto.*;
import com.br.pdvpostocombustivel.domain.entity.*;
import com.br.pdvpostocombustivel.domain.repository.*;
import com.br.pdvpostocombustivel.enums.TipoVenda;
import com.br.pdvpostocombustivel.exceptions.VendaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;

    public VendaService(VendaRepository vendaRepository,
                        ProdutoRepository produtoRepository,
                        EstoqueRepository estoqueRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Transactional
    public VendaResponse create(VendaRequest req) {
        if (req.itens() == null || req.itens().isEmpty()) {
            throw new VendaException("A venda precisa conter ao menos um item.");
        }

        Venda venda = new Venda(req.tipoVenda());

        for (ItemVendaRequest itemReq : req.itens()) {
            Produto produto = produtoRepository.findById(itemReq.produtoId())
                    .orElseThrow(() -> new VendaException("Produto não encontrado. ID=" + itemReq.produtoId()));

            Estoque estoque = estoqueRepository.findByProdutoId(produto.getId())
                    .orElseThrow(() -> new VendaException("Não há estoque vinculado ao produto: " + produto.getNome()));

            if (estoque.getQuantidade().compareTo(itemReq.quantidade()) < 0) {
                throw new VendaException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // desconta do estoque
            estoque.setQuantidade(estoque.getQuantidade().subtract(itemReq.quantidade()));
            estoqueRepository.save(estoque);

            ItemVenda item = new ItemVenda(produto, itemReq.quantidade(), itemReq.valorUnitario());
            venda.adicionarItem(item);
        }

        venda.recalcularTotal();
        vendaRepository.save(venda);

        return toResponse(venda);
    }

    private VendaResponse toResponse(Venda v) {
        List<ItemVendaResponse> itens = v.getItens().stream()
                .map(i -> new ItemVendaResponse(
                        i.getProduto().getNome(),
                        i.getQuantidade(),
                        i.getValorUnitario(),
                        i.getSubtotal()
                ))
                .collect(Collectors.toList());

        return new VendaResponse(
                v.getId(),
                v.getTipoVenda(),
                v.getDataHora(),
                v.getValorTotal(),
                itens
        );
    }

    @Transactional(readOnly = true)
    public List<VendaResponse> listAll() {
        return vendaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }
}
