package com.br.pdvpostocombustivel.api.pessoa.controller;

import com.br.pdvpostocombustivel.api.pessoa.dto.BombaResponse;
import com.br.pdvpostocombustivel.api.pessoa.service.BombaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bombas")
public class BombaController {

    private final BombaService bombaService;

    public BombaController(BombaService bombaService) {
        this.bombaService = bombaService;
    }

    @GetMapping
    public List<BombaResponse> listar() {
        return bombaService.listarTodas();
    }

    @GetMapping("/{id}")
    public BombaResponse buscarPorId(@PathVariable Long id) {
        return bombaService.buscarPorId(id);
    }
}
