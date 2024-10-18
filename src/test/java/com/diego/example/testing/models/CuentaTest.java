package com.diego.example.testing.models;

// import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import com.diego.example.testing.exceptions.SaldoInsuficienteExpception;
import com.github.javafaker.Faker;

class CuentaTest {

  Faker faker = new Faker();

  @Test
  public void testNombreCuenta() {

    Cuenta cuenta = new Cuenta();
    String name = faker.name().fullName();
    cuenta.setPersona(name);
    String nombre = cuenta.getPersona();
    assertEquals(name, nombre);
    assertTrue(name.equals(nombre));
  }

  @Test
  public void testSaldoCuenta() {
    String name = faker.name().fullName();
    Long saldo = faker.number().randomNumber(10, false);
    Cuenta cuenta = new Cuenta(name, new BigDecimal(saldo.toString()));

    assertEquals(saldo.toString(), cuenta.getSaldo().toString());

  }

  @Test
  public void testReferenciaCuenta() {
    String name = faker.name().fullName();
    Long saldo = faker.number().randomNumber(10, false);
    Cuenta cuenta = new Cuenta(name, new BigDecimal(saldo.toString()));
    Cuenta cuenta2 = new Cuenta(name, new BigDecimal(saldo.toString()));

    assertEquals(cuenta, cuenta2);

  }

  @Test
  public void testDebitoCuenta() {
    String name = faker.name().fullName();
    BigDecimal saldo = new BigDecimal("80000");
    Cuenta cuenta = new Cuenta(name, saldo);
    BigDecimal movimiento = new BigDecimal("200");
    cuenta.transferirSaldo(movimiento);
    assertEquals(saldo.subtract(movimiento), cuenta.getSaldo());

  }

  @Test
  public void testDineroInsuficienteException() {
    String name = faker.name().fullName();
    BigDecimal saldo = new BigDecimal("80000");
    Cuenta cuenta = new Cuenta(name, saldo);

    Exception exception = assertThrows(SaldoInsuficienteExpception.class, () -> {
      BigDecimal movimiento = new BigDecimal("90000");
      cuenta.transferirSaldo(movimiento);
    });

    assertEquals(exception.getMessage(), "Saldo insuficiente");

  }

  @Test
  public void testTransferenciaEntreCuenta() {
    String name = faker.name().fullName();
    BigDecimal saldo = new BigDecimal("80000");
    Cuenta cuenta = new Cuenta(name, saldo);

    String name2 = faker.name().fullName();
    BigDecimal saldo2 = new BigDecimal("180000");
    Cuenta cuenta2 = new Cuenta(name2, saldo2);

    Banco banco = new Banco("Santander", List.of(cuenta, cuenta2));

    banco.transferirSaldo(cuenta, cuenta2, new BigDecimal("70000"));

    assertEquals(new BigDecimal("10000"), cuenta.getSaldo());
    assertEquals(new BigDecimal("250000"), cuenta2.getSaldo());

  }
}
