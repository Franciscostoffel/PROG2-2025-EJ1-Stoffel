package org.example.service;

import org.example.pagos.*;

public class PagoManager {
    public void processPayment(double amount, String provider) {
        PaymentGateway gateway;
        PaymentProcessor processor;

        switch (provider.toLowerCase()) {
            case "paypal":
                gateway = new PaypalGateway();
                processor = new PaypalPaymentProcessor(gateway);
                break;
            case "mercadopago":
                gateway = new MercadoPagoGateway();
                processor = new MercadoPago(gateway);
                break;
            default:
                System.out.println("Proveedor de pago no soportado.");
                return;
        }

        boolean resultado = processor.processPayment(amount);
        if (resultado) {
            System.out.println("Pago procesado con éxito.");
        } else {
            System.out.println("Falló el procesamiento del pago.");
        }
    }
}
