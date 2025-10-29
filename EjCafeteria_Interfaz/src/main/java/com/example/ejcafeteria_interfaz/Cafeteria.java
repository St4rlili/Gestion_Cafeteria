package com.example.ejcafeteria_interfaz;

import java.util.ArrayList;
import java.util.List;

public class Cafeteria {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Cliente> clientesEnAtencion = new ArrayList<>();
    private CafeteriaTrigger trigger;

    public void setTrigger(CafeteriaTrigger trigger){
        this.trigger = trigger;
    }

    public void agregarCliente(Cliente c) {
        clientes.add(c);
        if (trigger != null) trigger.clienteLlega(c);
    }

    public void quitarCLientes(Cliente c) {
        clientes.remove(c);
        clientesEnAtencion.remove(c);

        if (trigger != null && !c.fueAtendido()) {
            trigger.clienteSeVa(c);
        }
    }

    public Cliente obtenerSiguiente() {
        for (Cliente c : clientes) {
            if (!clientesEnAtencion.contains(c) && !c.fueAtendido() && !c.estaEnProceso()) {
                c.marcarEnProceso(true);
                clientesEnAtencion.add(c);

                if (trigger != null) trigger.clienteSiendoAtendido(c);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                return c;
            }
        }
        return null;
    }

    public void liberarCliente(Cliente c) {
        c.marcarEnProceso(false);
        clientesEnAtencion.remove(c);
    }

    public boolean hayClientes() {
        return !clientes.isEmpty();
    }

    public boolean contieneCliente(Cliente c) {
        return clientes.contains(c);
    }

    public CafeteriaTrigger getTrigger() {
        return trigger;
    }
}