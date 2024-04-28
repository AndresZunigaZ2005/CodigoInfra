package model;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Cliente 1", 4));
        clientes.add(new Cliente("Cliente 2", 2));
        clientes.add(new Cliente("Cliente 3", 1));
        clientes.add(new Cliente("Cliente 4", 6));
        clientes.add(new Cliente("Cliente 5", 3));
        clientes.add(new Cliente("Cliente 6", 9));
        clientes.add(new Cliente("Cliente 7", 2));
        clientes.add(new Cliente("Cliente 8", 10));
        clientes.add(new Cliente("Cliente 9", 1));
        clientes.add(new Cliente("Cliente 10", 3));

        Supermercado supermercado = new Supermercado(clientes);
        List<Cajero> cajeros = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            cajeros.add(new Cajero("Cajero " + i, supermercado));
        }

        for (Cajero cajero : cajeros) {
            cajero.start();
        }

        for (Cajero cajero : cajeros) {
            try {
                cajero.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Todos los clientes han sido atendidos");
    }
}
