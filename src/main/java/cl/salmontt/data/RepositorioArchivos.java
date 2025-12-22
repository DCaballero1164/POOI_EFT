package cl.salmontt.data;

import cl.salmontt.model.entities.*;
import cl.salmontt.model.order.OrdenDeCompra;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivos {
    private static final String RUTA = "src/main/resources/";

    // --- ESCRITURA ---
    public void guardarTodo(List<Registrable> entidades) {
        List<Registrable> personas = new ArrayList<>();
        List<Registrable> unidades = new ArrayList<>();
        List<Registrable> productos = new ArrayList<>();
        List<Registrable> ordenes = new ArrayList<>();

        // Clasificación de entidades por tipo
        for (Registrable r : entidades) {
            if (r instanceof Empleado || r instanceof Proveedor) {
                personas.add(r);
            } else if (r instanceof CentroCultivo || r instanceof PlantaProceso) {
                unidades.add(r);
            } else if (r instanceof Producto) {
                productos.add(r);
            } else if (r instanceof OrdenDeCompra) {
                ordenes.add(r);
            }
        }

        // Llamada a escritura por cada archivo
        escribirArchivo(personas, "personas.txt");
        escribirArchivo(unidades, "unidadesoperativas.txt");
        escribirArchivo(productos, "productos.txt");
        escribirArchivo(ordenes, "ordenes.txt");
    }

    private void escribirArchivo(List<Registrable> lista, String nombre) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA + nombre))) {
            pw.println("...::: " + nombre.toUpperCase() + " :::...");

            for (Registrable r : lista) {
                if (r instanceof Empleado e) {
                    pw.println("EMPLEADO;" + e.getNombre() + ";" + e.getCargo() + ";" + e.getCorreo());
                } else if (r instanceof Proveedor p) {
                    pw.println("PROVEEDOR;" + p.getNombre() + ";" + p.getRubro() + ";" + p.getCorreo());
                } else if (r instanceof CentroCultivo c) {
                    pw.println("CENTRO;" + c.getNombre() + ";" + c.getComuna() + ";" + c.getToneladasProduccion());
                } else if (r instanceof PlantaProceso pl) {
                    pw.println("PLANTA;" + pl.getNombre() + ";" + pl.getComuna() + ";" + pl.getCapacidadProceso());
                } else if (r instanceof Producto pr) {
                    pw.println("PROD;" + pr.getCodigo() + ";" + pr.getNombre() + ";" + pr.getPrecio() + ";" + pr.getStock());
                } else if (r instanceof OrdenDeCompra o) {
                    // Lógica para serializar los productos de la orden en una sola línea
                    StringBuilder sb = new StringBuilder();
                    List<Producto> listaProds = o.getProductos();

                    if (listaProds != null && !listaProds.isEmpty()) {
                        for (int i = 0; i < listaProds.size(); i++) {
                            sb.append(listaProds.get(i).getNombre());
                            if (i < listaProds.size() - 1) sb.append(",");
                        }
                    } else {
                        sb.append("Ninguno");
                    }
                    pw.println("ORDEN;" + o.getId() + ";" + o.getNombreCliente() + ";" + sb.toString());
                }
            }
        } catch (IOException e) {
            System.err.println("Error guardando " + nombre + ": " + e.getMessage());
        }
    }

    // --- LECTURA ---
    public List<Registrable> cargarEntidades(String nombreArchivo) {
        List<Registrable> lista = new ArrayList<>();
        File file = new File(RUTA + nombreArchivo);

        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // Saltar cabecera decorativa
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] d = line.split(";");

                try {
                    switch (d[0]) {
                        case "EMPLEADO" -> lista.add(new Empleado(d[1], d[2], d[3]));
                        case "PROVEEDOR" -> lista.add(new Proveedor(d[1], d[2], d[3]));
                        case "CENTRO" -> lista.add(new CentroCultivo(d[1], d[2], Integer.parseInt(d[3])));
                        case "PLANTA" -> lista.add(new PlantaProceso(d[1], d[2], Integer.parseInt(d[3])));
                        case "PROD" -> lista.add(new Producto(d[1], d[2], Double.parseDouble(d[3]), Integer.parseInt(d[4])));
                        case "ORDEN" -> {
                            int id = Integer.parseInt(d[1]);
                            String cliente = d[2];
                            List<Producto> productosRecuperados = new ArrayList<>();

                            // Verificamos si existe la columna de productos y no está vacía
                            if (d.length > 3 && !d[3].equals("Ninguno")) {
                                String[] nombresProductos = d[3].split(",");
                                for (String nombreP : nombresProductos) {
                                    // Creamos un objeto Producto básico solo con el nombre para la lista
                                    // (Los demás campos como precio pueden ir en 0 o default)
                                    productosRecuperados.add(new Producto("0", nombreP.trim(), 0.0, 0));
                                }
                            }

                            // Ahora pasamos la lista reconstruida al constructor
                            lista.add(new OrdenDeCompra(id, cliente, productosRecuperados));
                        }
                    }
                } catch (Exception ex) {
                    System.err.println("Error al procesar línea: " + line);
                }
            }
        } catch (Exception e) {
            System.err.println("Error cargando " + nombreArchivo + ": " + e.getMessage());
        }
        return lista;
    }
}