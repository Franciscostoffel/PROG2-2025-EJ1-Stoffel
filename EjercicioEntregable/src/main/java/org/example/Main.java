package org.example;

import org.example.envios.AirShippingStrategy;
import org.example.envios.ShippingStrategy;
import org.example.envios.TruckShippingStrategy;
import org.example.service.EnvioLogica;
import org.example.service.PagoManager;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear estrategias de envío
            ShippingStrategy envioAvion = new AirShippingStrategy();
            ShippingStrategy envioCamion = new TruckShippingStrategy();
            // Crear gestor de envíos
            EnvioLogica calculadora = new EnvioLogica(envioAvion);
            // Crear gestor de pagos (clase lógica)
            PagoManager pagoManager = new PagoManager();
            // Ejecución concurrente para envíos y pagos
            CompletableFuture<Void> envioAvionTask = CompletableFuture.runAsync(() -> {
                double costo = calculadora.calcular(15, 2, "Buenos Aires", "Córdoba");
                System.out.println("Costo de envío por avión: $" + costo);
            });
            CompletableFuture<Void> envioCamionTask = CompletableFuture.runAsync(() -> {
                calculadora.setStrategy(envioCamion);
                double costo = calculadora.calcular(20, 3, "Rosario", "Mendoza");
                System.out.println("Costo de envío por camión: $" + costo);
            });
            CompletableFuture<Void> pagoPaypalTask = CompletableFuture.runAsync(() -> {
                pagoManager.processPayment(1800, "paypal");
            });
            CompletableFuture<Void> pagoMercadoPagoTask = CompletableFuture.runAsync(() -> {
                pagoManager.processPayment(1250, "mercadopago");
            });
            // Esperar a que finalicen todas
            CompletableFuture.allOf(envioAvionTask, envioCamionTask, pagoPaypalTask, pagoMercadoPagoTask).join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
