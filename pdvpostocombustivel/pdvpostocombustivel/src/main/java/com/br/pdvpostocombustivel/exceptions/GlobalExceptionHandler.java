package com.br.pdvpostocombustivel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handler para PessoaException
    @ExceptionHandler(PessoaException.class)
    public ResponseEntity<Object> handlePessoaException(PessoaException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Erro de validação de pessoa",
                ex.getMessage()
        );
    }

    // Handler para ContatoException
    @ExceptionHandler(ContatoException.class)
    public ResponseEntity<Object> handleContatoException(ContatoException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Erro de validação de contato",
                ex.getMessage()
        );
    }

    // Handler para ProdutoException
    @ExceptionHandler(ProdutoException.class)
    public ResponseEntity<Object> handleProdutoException(ProdutoException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Erro de validação de produto",
                ex.getMessage()
        );
    }

    // Handler para EstoqueException
    @ExceptionHandler(EstoqueException.class)
    public ResponseEntity<Object> handleEstoqueException(EstoqueException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Erro de validação de estoque",
                ex.getMessage()
        );
    }

    // Handler para PrecoException
    @ExceptionHandler(PrecoException.class)
    public ResponseEntity<Object> handlePrecoException(PrecoException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Erro de validação de preço",
                ex.getMessage()
        );
    }

    // Handler para CustoException
    @ExceptionHandler(CustoException.class)
    public ResponseEntity<Object> handleCustoException(CustoException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Erro de validação de custo",
                ex.getMessage()
        );
    }

    // Handler para AcessoException
    @ExceptionHandler(AcessoException.class)
    public ResponseEntity<Object> handleAcessoException(AcessoException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.FORBIDDEN,
                "Erro de acesso",
                ex.getMessage()
        );
    }

    // Handler para validações do Bean Validation (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        String erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Erro de validação",
                erros
        );
    }

    // Handler para IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Argumento inválido",
                ex.getMessage()
        );
    }

    // Handler genérico para erros inesperados (mas ignora exceções do Swagger)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        String className = ex.getClass().getName();

        // Ignora exceções do Swagger/SpringDoc para não quebrar a documentação
        if (className.contains("springdoc") || className.contains("swagger")) {
            throw ex;
        }

        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro inesperado",
                ex.getMessage()
        );
    }

    // Metodo auxiliar para construir a resposta de erro
    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String erro, String mensagem) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("erro", erro);
        body.put("mensagem", mensagem);
        return new ResponseEntity<>(body, status);
    }
}