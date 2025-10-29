package com.example.ejcafeteria_interfaz;

public interface CafeteriaTrigger {
    void clienteLlega(Cliente c);
    void clienteSiendoAtendido(Cliente c);
    void clienteSeVa(Cliente c);
    void clienteAtendido(Cliente c);
    void camareroTrabajando(Camarero cam);
    void camareroTermina(Camarero cam);
}
