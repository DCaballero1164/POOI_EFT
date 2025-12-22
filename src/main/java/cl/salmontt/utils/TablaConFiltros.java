package cl.salmontt.utils;

import cl.salmontt.model.entities.*;
import cl.salmontt.model.order.OrdenDeCompra;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class TablaConFiltros {

    private static JTable tabla;
    private static DefaultTableModel modelo;
    private static TableRowSorter<TableModel> sorter;

    // Mapa de títulos: Ahora definimos exactamente 5 columnas por tipo
    private static final Map<String, String[]> TITULOS_ESPECIFICOS = new HashMap<>();

    static {
        // Formato: {Col0, Col1, Col2, Col3, Col4}
        TITULOS_ESPECIFICOS.put("CentroCultivo", new String[]{"Categoría", "Nombre Centro", "Comuna", "Producción [Ton]", " - "});
        TITULOS_ESPECIFICOS.put("PlantaProceso", new String[]{"Categoría", "Nombre Planta", "Comuna", "Capacidad [Ton]", " - "});
        TITULOS_ESPECIFICOS.put("Empleado",      new String[]{"Categoría", "Nombre", "Cargo", "Correo", " - "});
        TITULOS_ESPECIFICOS.put("Proveedor",     new String[]{"Categoría", "Empresa", "Rubro", "Correo", " - "});
        TITULOS_ESPECIFICOS.put("Producto",      new String[]{"Categoría", "Producto", "Código", "Precio [$]", "Stock"});
        TITULOS_ESPECIFICOS.put("Orden",         new String[]{"Categoría", "ID Orden", "Cliente", "Productos Solicitados", "Estado"});
        TITULOS_ESPECIFICOS.put("Todos",         new String[]{"Tipo", "Nombre/ID", "-", "-", "-"});
    }

    private static void actualizarTitulos(String tipo) {
        String[] nuevosTitulos = TITULOS_ESPECIFICOS.getOrDefault(tipo, TITULOS_ESPECIFICOS.get("Todos"));
        TableColumnModel columnModel = tabla.getColumnModel();

        // Actualizamos las 5 columnas
        for (int i = 0; i < nuevosTitulos.length; i++) {
            columnModel.getColumn(i).setHeaderValue(nuevosTitulos[i]);
        }
        tabla.getTableHeader().repaint();
    }

    public static void mostrarTabla(List<Registrable> entidades) {
        // Definimos solo 5 encabezados iniciales
        String[] columnasIniciales = TITULOS_ESPECIFICOS.get("Todos");

        Object[][] filas = entidades.stream()
                .map(e -> {
                    if (e instanceof CentroCultivo cc) {
                        return new Object[]{"Centro", cc.getNombre(), cc.getComuna(), cc.getToneladasProduccion(), "-"};
                    } else if (e instanceof PlantaProceso pp) {
                        return new Object[]{"Planta", pp.getNombre(), pp.getComuna(), pp.getCapacidadProceso(), "-"};
                    } else if (e instanceof Empleado em) {
                        return new Object[]{"Empleado", em.getNombre(), em.getCargo(), em.getCorreo(), "-"};
                    } else if (e instanceof Proveedor pr) {
                        return new Object[]{"Proveedor", pr.getNombre(), pr.getRubro(), pr.getCorreo(), "-"};
                    } else if (e instanceof Producto p) {
                        return new Object[]{"Producto", p.getNombre(), p.getCodigo(), p.getPrecio(), p.getStock()};
                    } else if (e instanceof OrdenDeCompra o) {
                        // Unimos nombres de productos por coma
                        String listaProds = o.getProductos().stream()
                                .map(Producto::getNombre)
                                .collect(Collectors.joining(", "));

                        return new Object[]{"Orden", o.getId(), o.getNombreCliente(), listaProds, "Emitida"};
                    }
                    return new Object[]{"?", "?", "?", "?", "?"};
                })
                .toArray(Object[][]::new);

        modelo = new DefaultTableModel(filas, columnasIniciales);
        tabla = new JTable(modelo);
        sorter = new TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        actualizarTitulos("Todos");

        // Panel de Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] nombresBotones = {"Todos", "Centros", "Plantas", "Empleados", "Proveedores", "Productos", "Órdenes"};
        String[] filtros = {null, "Centro", "Planta", "Empleado", "Proveedor", "Producto", "Orden"};

        for (int i = 0; i < nombresBotones.length; i++) {
            JButton btn = new JButton(nombresBotones[i]);
            final String filtro = filtros[i];
            final String tipoHeader = (i == 0) ? "Todos" :
                    (i == 1) ? "CentroCultivo" :
                            (i == 2) ? "PlantaProceso" :
                                    (i == 3) ? "Empleado" :
                                            (i == 4) ? "Proveedor" :
                                                    (i == 5) ? "Producto" : "Orden";

            btn.addActionListener(ev -> {
                if (filtro == null) sorter.setRowFilter(null);
                else sorter.setRowFilter(RowFilter.regexFilter(filtro, 0));
                actualizarTitulos(tipoHeader);
            });
            panelBotones.add(btn);
        }

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(900, 400));

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelBotones, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panelPrincipal, "SalmonttApp - Visor de Datos", JOptionPane.PLAIN_MESSAGE);
    }
}