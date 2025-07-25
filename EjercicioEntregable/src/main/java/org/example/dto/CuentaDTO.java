package org.example.dto;

public class CuentaDTO {
    private int id;
    private double saldo;
    private String tipo;

    public CuentaDTO(int id, double saldo, String tipo) {
        this.id = id;
        this.saldo = saldo;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTipo() {
        return tipo;
    }
}
