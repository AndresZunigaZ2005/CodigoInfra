package model;

import java.util.List;

public class Supermercado{
    private List<Cliente> clientes;
    private int clientesAtendidos = 0;

    public Supermercado(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public synchronized Cliente siguienteCliente() {
        if (clientesAtendidos >= clientes.size()) {
            return null;
        }
        return clientes.get(clientesAtendidos++);
    }
}
