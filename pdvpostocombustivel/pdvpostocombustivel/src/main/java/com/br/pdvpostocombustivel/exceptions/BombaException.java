package com.br.pdvpostocombustivel.exceptions;

public class BombaException extends RuntimeException {

  public BombaException(String message) {
    super(message);
  }

  public BombaException(String message, Throwable cause) {
    super(message, cause);
  }
}
