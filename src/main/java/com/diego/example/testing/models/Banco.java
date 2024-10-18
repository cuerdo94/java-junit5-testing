package com.diego.example.testing.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Banco {

  String nombre;
  List<Cuenta> cuentas = new ArrayList<>();

  public void transferirSaldo(Cuenta origen, Cuenta destino, BigDecimal monto) {
    origen.validarSaldoException(monto);
    origen.transferirSaldo(monto);
    destino.recepcionarlSaldo(monto);
  }

  public void validarCuentaOrigen(Cuenta origen) {

  }

  // public void restituirSaldo(Cuenta origen, Cuenta destino, BigDecimal monto) {

  // origen.validarSaldoException(monto);
  // destino.recepcionarlSaldo(monto);
  // }

}
