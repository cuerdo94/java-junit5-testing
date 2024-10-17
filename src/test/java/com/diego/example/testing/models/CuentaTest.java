package com.diego.example.testing.models;

// import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

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
    cuenta.debito(movimiento);
    assertEquals(saldo.subtract(movimiento), cuenta.getSaldo());

  }

  @Test
  public void testDineroInsuficienteException() {
    String name = faker.name().fullName();
    BigDecimal saldo = new BigDecimal("80000");
    Cuenta cuenta = new Cuenta(name, saldo);

    Exception exception = assertThrows(SaldoInsuficienteExpception.class, () -> {
      BigDecimal movimiento = new BigDecimal("90000");
      cuenta.debito(movimiento);
    });

    assertEquals(exception.getMessage(), "Saldo insuficiente");

  }
}
