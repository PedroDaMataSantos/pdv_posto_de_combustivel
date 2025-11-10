package com.br.pdvpostocombustivel.api.pessoa.controller;

import com.br.pdvpostocombustivel.api.pessoa.dto.AcessoRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.AcessoResponse;
import com.br.pdvpostocombustivel.api.pessoa.service.AcessoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/acessos")
public class AcessoController {

    private final AcessoService service;

    public AcessoController(AcessoService service) {
        this.service = service;
    }

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public AcessoResponse registrar(@RequestBody AcessoRequest req) {
        return service.registrar(req);
    }

    @GetMapping
    public List<AcessoResponse> list() {
        return service.listAll();
    }

    @PostMapping("/login")
    public AcessoResponse login(@RequestBody AcessoRequest req) {
        return service.autenticar(req.usuario(), req.senha());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
