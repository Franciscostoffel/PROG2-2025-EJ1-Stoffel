package org.example.envios;

public interface ShippingStrategy {
    double calculateCost(double pesoKg, double volumenM3, String origen, String destino);
}