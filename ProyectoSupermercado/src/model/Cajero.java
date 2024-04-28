package model;

public class Cajero extends Thread{
    private String nombre;
    private Supermercado supermercado;

    public Cajero(String nombre, Supermercado supermercado) {
        this.nombre = nombre;
        this.supermercado = supermercado;
    }

    public void run() {
        while (true) {
            Cliente cliente = supermercado.siguienteCliente();
            if (cliente == null) {
                break;
            }
            System.out.println(nombre + " está atendiendo a " + cliente.getNombre());
            for (int i = 0; i < cliente.getCantidadProductos(); i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(nombre + " terminó de atender un producto de " + cliente.getNombre());
            }
            System.out.println(nombre + " terminó de atender a " + cliente.getNombre());
        }
    }
}
