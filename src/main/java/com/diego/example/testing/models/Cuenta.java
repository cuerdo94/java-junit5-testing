package com.diego.example.testing.models;

import java.math.BigDecimal;

import com.diego.example.testing.exceptions.SaldoInsuficienteExpception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cuenta {
  private String persona;
  private BigDecimal saldo;

  public void debito(BigDecimal movimiento) {

    if (saldo.compareTo(movimiento) < 0) {
      throw new SaldoInsuficienteExpception("Saldo insuficiente");
    }
    saldo = saldo.subtract(movimiento);
  }
}
