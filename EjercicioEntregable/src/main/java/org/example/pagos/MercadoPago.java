package org.example.pagos;

public class MercadoPago implements PaymentProcessor {
    private final PaymentGateway gateway;

    public MercadoPago(PaymentGateway gateway) {
        this.gateway = gateway;
    }
    @Override
    public boolean processPayment(double amount) {
        return gateway.authorize(amount) && gateway.capture(amount);
    }
}

