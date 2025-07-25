package org.example.envios;

public class BoatShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateCost(double pesoKg, double volumenM3, String origen, String destino) {
        double base = 30;
        return base + (pesoKg * 1.5) + (volumenM3 * 3);
    }
}