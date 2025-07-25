package org.example.service;
import org.example.dto.CuentaDTO;
import org.example.entidades.CajaDeAhorro;
import org.example.entidades.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class LogicaCuenta {
    private static LogicaCuenta instancia;
    private final List<Cuenta> cuentas = new ArrayList<>();

    private LogicaCuenta() {}

    // Singleton con doble chequeo
    public static LogicaCuenta getInstancia() {
        if (instancia == null) {
            synchronized (LogicaCuenta.class) {
                if (instancia == null) {
                    instancia = new LogicaCuenta();
                }
            }
        }
        return instancia;
    }

    public synchronized void agregarCuenta(Cuenta cuenta) throws Exception {
        for (Cuenta c : cuentas) {
            if (c.getId() == cuenta.getId()) {
                throw new Exception("Ya existe una cuenta con ese ID");
            }
        }
        cuentas.add(cuenta);
    }

    public synchronized void agregarSaldo(int id, double monto) throws Exception {
        Cuenta cuenta = buscarCuentaPorId(id);
        cuenta.agregarSaldo(monto);
    }

    public synchronized void quitarSaldo(int id, double monto) throws Exception {
        Cuenta cuenta = buscarCuentaPorId(id);
        cuenta.quitarSaldo(monto);
    }

    public synchronized CuentaDTO obtenerCuentaDTO(int id) throws Exception {
        Cuenta cuenta = buscarCuentaPorId(id);
        String tipo = cuenta instanceof CajaDeAhorro ? "CajaDeAhorro" : "CuentaCorriente";
        return new CuentaDTO(cuenta.getId(), cuenta.getSaldo(), tipo);
    }

    private Cuenta buscarCuentaPorId(int id) throws Exception {
        return cuentas.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new Exception("Cuenta no encontrada"));
    }

    public synchronized List<CuentaDTO> obtenerTodas() {
        List<CuentaDTO> dtos = new ArrayList<>();
        for (Cuenta c : cuentas) {
            String tipo = c instanceof CajaDeAhorro ? "CajaDeAhorro" : "CuentaCorriente";
            dtos.add(new CuentaDTO(c.getId(), c.getSaldo(), tipo));
        }
        return dtos;
    }
}
