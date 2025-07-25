package org.example.pagos;

public class MercadoPagoPaymentProcessor extends PaymentProcessor {

    public MercadoPagoPaymentProcessor(PaymentGateway gateway) {
        super(gateway);
    }

    @Override
    public boolean processPayment(double amount) {
        return gateway.authorize(amount) && gateway.capture(amount);
    }
    @Override
    public boolean refundPayment(double amount) {
        System.out.println("Reembolsando $" + amount + " a través de MercadoPago");
        return true;
    }
}
