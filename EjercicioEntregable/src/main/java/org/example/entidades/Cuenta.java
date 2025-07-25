package org.example.entidades;

public abstract class Cuenta {
    protected int id;
    protected double saldo;

    public Cuenta(int id, double saldoInicial) {
        this.id = id;
        this.saldo = saldoInicial;
    }
    public int getId() {
        return id;
    }
    public double getSaldo() {
        return saldo;
    }
    public synchronized void agregarSaldo(double monto) {
        saldo += monto;
    }
    public synchronized void quitarSaldo(double monto) throws Exception {
        if (saldo < monto) {
            throw new Exception("Saldo insuficiente");
        }
        saldo -= monto;
    }
}
