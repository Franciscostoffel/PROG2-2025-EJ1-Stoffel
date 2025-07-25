package org.example.service;

import org.example.envios.ShippingStrategy;

public class EnvioLogica {
    private ShippingStrategy strategy;

    public EnvioLogica(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calcular(double pesoKg, double volumenM3, String origen, String destino) {
        return strategy.calculateCost(pesoKg, volumenM3, origen, destino);
    }

    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
    }
}
