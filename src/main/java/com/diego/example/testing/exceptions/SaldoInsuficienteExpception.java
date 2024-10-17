package com.diego.example.testing.exceptions;

public class SaldoInsuficienteExpception extends RuntimeException {

  public SaldoInsuficienteExpception(String mensaje) {
    super(mensaje);
  }

}
