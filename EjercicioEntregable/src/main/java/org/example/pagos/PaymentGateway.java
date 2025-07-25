package org.example.pagos;

public interface PaymentGateway {
    boolean authorize(double amount);
    boolean capture(double amount);
}
