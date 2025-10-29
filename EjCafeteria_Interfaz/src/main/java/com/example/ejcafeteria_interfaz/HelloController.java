package com.example.ejcafeteria_interfaz;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HelloController implements CafeteriaTrigger {

    @FXML private ListView<Cliente> listaClientesLlegan;
    @FXML private ListView<Cliente> listaClientesAtendidos;
    @FXML private ListView<Cliente> listaClientesSiendoAtendidos;
    @FXML private ListView<Cliente> listaClientesSeVan;
    @FXML private ListView<Camarero> listaCamarerosTrabajando;
    @FXML private ListView<Camarero> listaCamarerosTerminaron;

    private ObservableList<Cliente> clientesLlegan = FXCollections.observableArrayList();
    private ObservableList<Cliente> clientesAtendidos = FXCollections.observableArrayList();
    private ObservableList<Cliente> clientesSiendoAtendidos = FXCollections.observableArrayList();
    private ObservableList<Cliente> clientesSeVan = FXCollections.observableArrayList();
    private ObservableList<Camarero> camarerosTrabajando = FXCollections.observableArrayList();
    private ObservableList<Camarero> camarerosTerminaron = FXCollections.observableArrayList();

    @FXML void initialize() {
        listaClientesLlegan.setItems(clientesLlegan);
        listaClientesAtendidos.setItems(clientesAtendidos);
        listaClientesSiendoAtendidos.setItems(clientesSiendoAtendidos);
        listaClientesSeVan.setItems(clientesSeVan);
        listaCamarerosTrabajando.setItems(camarerosTrabajando);
        listaCamarerosTerminaron.setItems(camarerosTerminaron);
    }

    @FXML
    private void iniciar() throws InterruptedException {
        Cafeteria cafeteria = new Cafeteria();
        cafeteria.setTrigger(this);

        Cliente cliente1 = new Cliente("Pedro", 5, cafeteria);
        Cliente cliente2 = new Cliente("María", 10, cafeteria);
        Cliente cliente3 = new Cliente("Raquel", 12, cafeteria);
        Cliente cliente4 = new Cliente("Fernando", 8, cafeteria);
        Cliente cliente5 = new Cliente("Mateo", 10, cafeteria);
        Cliente cliente6 = new Cliente("Carmen", 25, cafeteria);

        Camarero cam1 = new Camarero("Camarero1", cafeteria);
        Camarero cam2 = new Camarero("Camarero2", cafeteria);

        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        cliente5.start();
        cliente6.start();

        cam1.start();
        Thread.sleep(1000);
        cam2.start();
    }

    @Override
    public void clienteLlega(Cliente c) {
        Platform.runLater(() -> clientesLlegan.add(c));
    }

    @Override
    public void clienteSiendoAtendido(Cliente c) {
        Platform.runLater(() -> {
            clientesLlegan.remove(c);
            clientesSiendoAtendidos.add(c);
        });
    }

    @Override
    public void clienteSeVa(Cliente c) {
        Platform.runLater(() -> {
            clientesLlegan.remove(c);
            clientesSiendoAtendidos.remove(c);
            clientesSeVan.add(c);
        });
    }

    @Override
    public void clienteAtendido(Cliente c) {
        Platform.runLater(() -> {
            clientesSiendoAtendidos.remove(c);
            clientesAtendidos.add(c);
        });
    }

    @Override
    public void camareroTrabajando(Camarero cam) {
        Platform.runLater(() -> camarerosTrabajando.add(cam));
    }

    @Override
    public void camareroTermina(Camarero cam) {
        Platform.runLater(() -> {
            camarerosTrabajando.remove(cam);
            camarerosTerminaron.add(cam);
        });
    }
}
