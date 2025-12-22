package cl.salmontt.model.order;

import cl.salmontt.model.entities.Registrable;
import cl.salmontt.model.entities.Producto;
import cl.salmontt.utils.Direccion;

import java.util.List;

public class OrdenDeCompra implements Registrable {
    private int id;
    private String nombreCliente;
    private List<Producto> productos;

    public OrdenDeCompra(int id, String nombreCliente, List<Producto> productos) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto p) {
        if (p != null) {
            this.productos.add(p);
        }
    }

    @Override
    public String mostrarResumen() {
        return "Orden #" + id + " | Cliente: " + nombreCliente + " | Cant. Productos: " + productos.size();
    }
}