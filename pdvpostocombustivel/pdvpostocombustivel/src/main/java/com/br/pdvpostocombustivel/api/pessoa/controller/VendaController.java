package com.br.pdvpostocombustivel.api.pessoa.controller;

import com.br.pdvpostocombustivel.api.pessoa.dto.VendaRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.VendaResponse;
import com.br.pdvpostocombustivel.api.pessoa.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendas")
public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendaResponse criar(@RequestBody VendaRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<VendaResponse> listar() {
        return service.listAll();
    }
}
