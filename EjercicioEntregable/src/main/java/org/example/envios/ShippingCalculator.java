package org.example.envios;

public class ShippingCalculator {
    private ShippingStrategy strategy;

    public ShippingCalculator(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calcular(double pesoKg, double volumenM3, String origen, String destino) {
        return strategy.calculateCost(pesoKg, volumenM3, origen, destino);
    }

    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
    }
}