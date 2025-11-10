package com.br.pdvpostocombustivel.api.pessoa.controller;

import com.br.pdvpostocombustivel.api.pessoa.dto.EstoqueRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.EstoqueResponse;
import com.br.pdvpostocombustivel.api.pessoa.service.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estoques")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstoqueResponse create(@RequestBody EstoqueRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public EstoqueResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<EstoqueResponse> listAll() {
        return service.listAll();
    }

    @PutMapping("/{id}")
    public EstoqueResponse update(@PathVariable Long id, @RequestBody EstoqueRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
