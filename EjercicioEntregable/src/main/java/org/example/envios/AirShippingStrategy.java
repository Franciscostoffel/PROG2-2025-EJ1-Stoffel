package org.example.envios;

public class AirShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateCost(double pesoKg, double volumenM3, String origen, String destino) {
        double base = 50;
        return base + (pesoKg * 5) + (volumenM3 * 10);
    }
}