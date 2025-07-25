package org.example.entidades;

public class CajaDeAhorro extends  Cuenta {

    public CajaDeAhorro(int id, double saldoInicial) {
            super(id, saldoInicial);
    }
    public static class Builder {
        private int id;
        private double saldoInicial;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setSaldoInicial(double saldoInicial) {
            this.saldoInicial = saldoInicial;
            return this;
        }

        public CajaDeAhorro build() {
            return new CajaDeAhorro(id, saldoInicial);
        }
    }
}

