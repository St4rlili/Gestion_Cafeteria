package com.example.ejcafeteria_interfaz;

public class Cliente extends Thread {
    private String nombre;
    private int tiempoEspera;
    private Cafeteria cafeteria;
    private boolean atendido = false;
    private boolean enProceso = false;
    private long tiempoInicio;

    public Cliente(String nombre, int tiempoEspera, Cafeteria cafeteria) {
        this.nombre = nombre;
        this.tiempoEspera = tiempoEspera * 1000;
        this.cafeteria = cafeteria;
    }

    @Override
    public void run() {
        int tiempoLlegada = (int) ((Math.random() + 1) * 2000);

        try {
            Thread.sleep(tiempoLlegada);
            System.out.println(nombre + " ha entrado en la cafetería");

            tiempoInicio = System.currentTimeMillis();

            cafeteria.agregarCliente(this);

            Thread.sleep(tiempoEspera);

            if (!atendido) {
                System.out.println(nombre + " se cansó de esperar y se fue después de " + tiempoEspera/1000 + " segundos");
                cafeteria.quitarCLientes(this);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getNombre() {
        return nombre;
    }

    public long getTiempoInicio() {
        return tiempoInicio;
    }

    public void marcarAtendido() {
        atendido = true;
    }

    public boolean fueAtendido() {
        return atendido;
    }

    public void marcarEnProceso(boolean estado) {
        enProceso = estado;
    }

    public boolean estaEnProceso() {
        return enProceso;
    }

    @Override
    public String toString() {
        return nombre;
    }
}