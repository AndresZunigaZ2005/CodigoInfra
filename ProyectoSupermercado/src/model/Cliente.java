package model;

public class Cliente {
    private String nombre;
    private int cantidadProductos;

    public Cliente(String nombre, int cantidadProductos) {
        this.nombre = nombre;
        this.cantidadProductos = cantidadProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }
}
