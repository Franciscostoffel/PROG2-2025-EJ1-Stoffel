import org.example.envios.AirShippingStrategy;
import org.example.envios.BoatShippingStrategy;
import org.example.envios.TruckShippingStrategy;
import org.example.service.EnvioLogica;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnviosTest {

    @Test
    void testTruckShippingStrategy() {
        EnvioLogica envio = new EnvioLogica(new TruckShippingStrategy());
        double costo = envio.calcular(10, 2, "A", "B");
        // base 20 + (10*2) + (2*5) = 20 + 20 + 10 = 50
        assertEquals(50, costo);
    }

    @Test
    void testAirShippingStrategy() {
        EnvioLogica envio = new EnvioLogica(new AirShippingStrategy());
        double costo = envio.calcular(4, 3, "A", "B");
        // base 50 + (4*5) + (3*10) = 50 + 20 + 30 = 100
        assertEquals(100, costo);
    }

    @Test
    void testBoatShippingStrategy() {
        EnvioLogica envio = new EnvioLogica(new BoatShippingStrategy());
        double costo = envio.calcular(8, 1, "A", "B");
        // base 30 + (8*1.5) + (1*3) = 30 + 12 + 3 = 45
        assertEquals(45, costo);
    }

    @Test
    void testChangeStrategy() {
        EnvioLogica envio = new EnvioLogica(new TruckShippingStrategy());
        double costo1 = envio.calcular(5, 1, "A", "B");
        // base 20 + (5*2) + (1*5) = 20 + 10 + 5 = 35
        assertEquals(35, costo1);

        envio.setStrategy(new AirShippingStrategy());
        double costo2 = envio.calcular(5, 1, "A", "B");
        // base 50 + (5*5) + (1*10) = 50 + 25 + 10 = 85
        assertEquals(85, costo2);
    }
}
