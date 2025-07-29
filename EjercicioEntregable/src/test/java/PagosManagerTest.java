import org.example.pagos.*;
import org.example.service.PagoManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PagosManagerTest {
    private PaymentGateway mercadoPagoGateway;
    private PaymentGateway paypalGateway;
    private PaymentProcessor mercadoPagoProcessor;
    private PaymentProcessor paypalProcessor;

    @BeforeEach
    void setUp() {
        mercadoPagoGateway = new MercadoPagoGateway();
        paypalGateway = new PaypalGateway();
        mercadoPagoProcessor = new MercadoPago(mercadoPagoGateway);
        paypalProcessor = new PaypalPaymentProcessor(paypalGateway);
    }

    @Test
    void testProcessPaymentPaypalSuccess() {
        PagoManager manager = new PagoManager();
        assertDoesNotThrow(() -> manager.processPayment(100.0, "paypal"));
    }
    @Test
    void testProcessPaymentMercadoPagoSuccess() {
        PagoManager manager = new PagoManager();
        assertDoesNotThrow(() -> manager.processPayment(200.0, "mercadopago"));
    }
    @Test
    void testProcessPaymentProveedorNoSoportado() {
        PagoManager manager = new PagoManager();
        assertDoesNotThrow(() -> manager.processPayment(150.0, "otro"));
        // No hay excepción, pero no procesa el pago
    }
    @Test
    void testProcessPaymentPaypalFail() {
        PagoManager manager = new PagoManager() {
            @Override
            public void processPayment(double amount, String provider) {
                PaymentGateway gateway;
                PaymentProcessor processor;
                switch (provider.toLowerCase()) {
                    case "paypal":
                        gateway = new PaymentGateway() {
                            @Override
                            public boolean authorize(double amount) {
                                return false;
                            }
                            @Override
                            public boolean capture(double amount) {
                                return false;
                            }
                        };
                        processor = new PaypalPaymentProcessor(gateway);
                        break;
                    default:
                        return;
                }
                boolean resultado = processor.processPayment(amount);
                assertFalse(resultado);
            }
        };
        manager.processPayment(100.0, "paypal");
    }
    @Test
    void testProcessPaymentMercadoPagoFail() {
        PagoManager manager = new PagoManager() {
            @Override
            public void processPayment(double amount, String provider) {
                PaymentGateway gateway;
                PaymentProcessor processor;
                switch (provider.toLowerCase()) {
                    case "mercadopago":
                        gateway = new PaymentGateway() {
                            @Override
                            public boolean authorize(double amount) {
                                return false;
                            }

                            @Override
                            public boolean capture(double amount) {
                                return false;
                            }
                        };
                        processor = new MercadoPago(gateway);
                        break;
                    default:
                        return;
                }
                boolean resultado = processor.processPayment(amount);
                assertFalse(resultado);
            }
        };
        manager.processPayment(100.0, "mercadopago");
    }
}
