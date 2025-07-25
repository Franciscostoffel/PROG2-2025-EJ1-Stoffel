package org.example.pagos;

public class PaypalGateway implements PaymentGateway {
    @Override
    public boolean authorize(double amount) {
        System.out.println("Autorizando pago de $" + amount + " con PayPal");
        return true;
    }

    @Override
    public boolean capture(double amount) {
        System.out.println("Capturando pago de $" + amount + " con PayPal");
        return true;
    }
}