package cl.salmontt.utils;

import cl.salmontt.model.entities.*;
import cl.salmontt.model.order.OrdenDeCompra;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Validaciones {

    public static CentroCultivo validarCentroCultivo() {
        try {
            String nombre = JOptionPane.showInputDialog(null, "Nombre: ", "Nuevo Centro de Cultivo", JOptionPane.PLAIN_MESSAGE);
            if (nombre == null || nombre.isBlank()) return null;

            String comuna = JOptionPane.showInputDialog(null, "Comuna: ", "Nuevo Centro de Cultivo", JOptionPane.PLAIN_MESSAGE);
            if (comuna == null || comuna.isBlank()) return null;

            String prodStr = JOptionPane.showInputDialog(null, "Toneladas producidas: ", "Nuevo Centro de Cultivo", JOptionPane.PLAIN_MESSAGE);
            if (prodStr == null) return null;

            int produccion = Integer.parseInt(prodStr);
            if (produccion <= 0) throw new NumberFormatException();

            return new CentroCultivo(nombre, comuna, produccion);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese una producción válida mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static PlantaProceso validarPlantaProceso() {
        try {
            String nombre = JOptionPane.showInputDialog(null, "Nombre: ", "Nueva Planta de Proceso", JOptionPane.PLAIN_MESSAGE);
            if (nombre == null || nombre.isBlank()) return null;

            String comuna = JOptionPane.showInputDialog(null, "Comuna: ", "Nueva Planta de Proceso", JOptionPane.PLAIN_MESSAGE);
            if (comuna == null || comuna.isBlank()) return null;

            String prodStr = JOptionPane.showInputDialog(null, "Toneladas mensuales: ", "Nueva Planta de Proceso", JOptionPane.PLAIN_MESSAGE);
            if (prodStr == null) return null;

            int produccion = Integer.parseInt(prodStr);
            if (produccion <= 0) throw new NumberFormatException();

            return new PlantaProceso(nombre, comuna, produccion);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static Empleado validarEmpleado() {
        String nombre = JOptionPane.showInputDialog(null, "Nombre: ", "Nuevo Empleado", JOptionPane.PLAIN_MESSAGE);
        String cargo = JOptionPane.showInputDialog(null, "Cargo: ", "Nuevo Empleado", JOptionPane.PLAIN_MESSAGE);
        String correo = JOptionPane.showInputDialog(null, "Correo: ", "Nuevo Empleado", JOptionPane.PLAIN_MESSAGE);

        if (nombre == null || cargo == null || correo == null || nombre.isBlank() || cargo.isBlank() || correo.isBlank()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return null;
        }
        return new Empleado(nombre, cargo, correo);
    }

    public static Proveedor validarProveedor() {
        String nombre = JOptionPane.showInputDialog(null, "Nombre: ", "Nuevo Proveedor", JOptionPane.PLAIN_MESSAGE);
        String rubro = JOptionPane.showInputDialog(null, "Rubro: ", "Nuevo Proveedor", JOptionPane.PLAIN_MESSAGE);
        String correo = JOptionPane.showInputDialog(null, "Correo: ", "Nuevo Proveedor", JOptionPane.PLAIN_MESSAGE);

        if (nombre == null || rubro == null || correo == null || nombre.isBlank() || rubro.isBlank() || correo.isBlank()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return null;
        }
        return new Proveedor(nombre, rubro, correo);
    }

    public static Producto validarProducto() {
        try {
            String cod = JOptionPane.showInputDialog(null, "Código:", "Nuevo Producto", JOptionPane.PLAIN_MESSAGE);
            String nom = JOptionPane.showInputDialog(null, "Nombre:", "Nuevo Producto", JOptionPane.PLAIN_MESSAGE);
            double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Precio:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Stock inicial:"));

            if (cod == null || nom == null || cod.isBlank() || nom.isBlank()) return null;
            return new Producto(cod, nom, precio, stock);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos numéricos inválidos.");
            return null;
        }
    }

    /**
     * Valida y crea una Orden de Compra, descontando stock de los productos.
     * @param entidades Lista polimórfica de la cual se extraen los productos disponibles.
     * @return Un objeto OrdenDeCompra o null si se cancela el proceso.
     */
    public static OrdenDeCompra validarOrdenDeCompra(List<Registrable> entidades) {
        // 1. Filtrar solo los productos disponibles en el sistema
        List<Producto> productosDisponibles = new ArrayList<>();
        for (Registrable r : entidades) {
            if (r instanceof Producto) {
                productosDisponibles.add((Producto) r);
            }
        }

        // Validación de existencia de productos
        if (productosDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No hay productos registrados. Debe ingresar productos antes de crear una orden.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // 2. Pedir nombre del Cliente
        String nombreCliente = JOptionPane.showInputDialog(null,
                "Ingrese el nombre del Cliente:", "Nueva Orden", JOptionPane.PLAIN_MESSAGE);

        if (nombreCliente == null || nombreCliente.isBlank()) {
            return null; // El usuario canceló o dejó vacío
        }

        // 3. Generar ID único y crear la instancia
        int idOrden = (int) (System.currentTimeMillis() % 10000);
        OrdenDeCompra nuevaOrden = new OrdenDeCompra(idOrden, nombreCliente.trim(), new ArrayList<>());

        // 4. Bucle para agregar productos con validación de Stock
        boolean agregando = true;
        while (agregando) {
            Producto p = (Producto) JOptionPane.showInputDialog(
                    null,
                    "Seleccione Producto para agregar a la orden:",
                    "Añadir Items (Stock disponible)",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    productosDisponibles.toArray(),
                    productosDisponibles.get(0)
            );

            if (p != null) {
                // --- Lógica de Negocio: Validación y Descuento de Stock ---
                if (p.getStock() > 0) {
                    // Restamos uno al stock del producto en memoria
                    p.setStock(p.getStock() - 1);
                    nuevaOrden.agregarProducto(p);

                    // Alerta si el stock llegó a cero
                    if (p.getStock() == 0) {
                        JOptionPane.showMessageDialog(null,
                                "ALERTA: El producto '" + p.getNombre() + "' se ha quedado sin stock.",
                                "Stock Agotado", JOptionPane.WARNING_MESSAGE);
                    }

                    int mas = JOptionPane.showConfirmDialog(null,
                            "¿Desea agregar otro producto a esta misma orden?",
                            "Continuar", JOptionPane.YES_NO_OPTION);

                    if (mas != JOptionPane.YES_OPTION) {
                        agregando = false;
                    }
                } else {
                    // Bloqueo: No hay stock
                    JOptionPane.showMessageDialog(null,
                            "ERROR: El producto '" + p.getNombre() + "' no tiene stock disponible.",
                            "Sin Unidades", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                agregando = false; // El usuario presionó cancelar en la selección de producto
            }
        }

        // 5. Retornar la orden solo si se agregaron productos
        if (nuevaOrden.getProductos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Orden cancelada: No se seleccionaron productos.");
            return null;
        }

        return nuevaOrden;
    }

    // Lógica para validar si el RUT es real
    public static boolean validarRut(int numero, char dv) {
        try {
            int m = 0, s = 1;
            int t = Integer.parseInt(String.valueOf(numero));
            for (; t != 0; t /= 10) {
                s = (s + t % 10 * (9 - m++ % 6)) % 11;
            }
            char dvCalculado = (char) (s != 0 ? s + 47 : 75);
            return dvCalculado == dv;
        } catch (Exception e) {
            return false;
        }
    }
}