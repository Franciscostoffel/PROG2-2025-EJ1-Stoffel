package org.example;

import org.example.dto.CuentaDTO;
import org.example.entidades.CajaDeAhorro;
import org.example.entidades.CuentaCorriente;
import org.example.service.LogicaCuenta;

public class Main {
    public static void main(String[] args) {
        try {
            LogicaCuenta logica = LogicaCuenta.getInstancia();

            // Crear cuentas usando Builder interno
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

            logica.agregarSaldo(1, 500);     // Caja: 1500
            logica.quitarSaldo(2, 700);      // Corriente: queda en -200

            CuentaDTO dto1 = logica.obtenerCuentaDTO(1);
            CuentaDTO dto2 = logica.obtenerCuentaDTO(2);

            System.out.println("Cuenta 1 - Tipo: " + dto1.getTipo() + ", Saldo: " + dto1.getSaldo());
            System.out.println("Cuenta 2 - Tipo: " + dto2.getTipo() + ", Saldo: " + dto2.getSaldo());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
