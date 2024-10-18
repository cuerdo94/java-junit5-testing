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

  public void transferirSaldo(BigDecimal movimiento) {
    validarSaldoException(movimiento);
    saldo = saldo.subtract(movimiento);
  }

  public void recepcionarlSaldo(BigDecimal movimiento) {
    saldo = saldo.add(movimiento);
  }

  public Boolean validarSaldo(BigDecimal movimiento) {
    return saldo.compareTo(movimiento) < 0;
  }

  public void validarSaldoException(BigDecimal movimiento) {
    if (validarSaldo(movimiento)) {
      throw new SaldoInsuficienteExpception("Saldo insuficiente");
    }
  }
}
