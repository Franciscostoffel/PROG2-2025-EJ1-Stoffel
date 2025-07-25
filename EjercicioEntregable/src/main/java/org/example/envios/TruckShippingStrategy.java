package org.example.envios;

public class TruckShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateCost(double pesoKg, double volumenM3, String origen, String destino) {
        double base = 20;
        return base + (pesoKg * 2) + (volumenM3 * 5);
    }
}
