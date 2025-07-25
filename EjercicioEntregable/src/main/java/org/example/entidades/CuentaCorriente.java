package org.example.entidades;

public class CuentaCorriente extends Cuenta {
    private double limiteDescubierto;

    public CuentaCorriente(int id, double saldoInicial, double limiteDescubierto) {
        super(id, saldoInicial);
        this.limiteDescubierto = limiteDescubierto;
    }

    @Override
    public synchronized void quitarSaldo(double monto) throws Exception {
        if (saldo + limiteDescubierto < monto) {
            throw new Exception("Saldo insuficiente incluyendo descubierto");
        }
        saldo -= monto;
    }

    public static class Builder {
        private int id;
        private double saldoInicial;
        private double limiteDescubierto;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setSaldoInicial(double saldoInicial) {
            this.saldoInicial = saldoInicial;
            return this;
        }

        public Builder setLimiteDescubierto(double limiteDescubierto) {
            this.limiteDescubierto = limiteDescubierto;
            return this;
        }

        public CuentaCorriente build() {
            return new CuentaCorriente(id, saldoInicial, limiteDescubierto);
        }
    }
}