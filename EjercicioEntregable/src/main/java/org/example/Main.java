package org.example;

import org.example.dto.CuentaDTO;
import org.example.entidades.CajaDeAhorro;
import org.example.entidades.CuentaCorriente;
import org.example.envios.AirShippingStrategy;
import org.example.envios.ShippingStrategy;
import org.example.envios.TruckShippingStrategy;
import org.example.pagos.*;
import org.example.service.EnvioLogica;
import org.example.service.LogicaCuenta;
import org.example.service.PagoManager;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        try {
            LogicaCuenta logica = LogicaCuenta.getInstancia();
            // Crear cuentas con builders internos
            CajaDeAhorro caja = new CajaDeAhorro.Builder()
                    .setId(1)
                    .setSaldoInicial(1000)
                    .build();

            CuentaCorriente corriente = new CuentaCorriente.Builder()
                    .setId(2)
                    .setSaldoInicial(500)
                    .setLimiteDescubierto(300)
                    .build();

            logica.agregarCuenta(caja);
            logica.agregarCuenta(corriente);

            logica.agregarSaldo(1, 500);       // CajaDeAhorro: 1500
            logica.quitarSaldo(2, 700);        // CuentaCorriente: queda en -200

            CuentaDTO dto1 = logica.obtenerCuentaDTO(1);
            CuentaDTO dto2 = logica.obtenerCuentaDTO(2);

            System.out.println("Cuenta 1 - Tipo: " + dto1.getTipo() + ", Saldo: " + dto1.getSaldo());
            System.out.println("Cuenta 2 - Tipo: " + dto2.getTipo() + ", Saldo: " + dto2.getSaldo());

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

//jgfkhgfhtgf