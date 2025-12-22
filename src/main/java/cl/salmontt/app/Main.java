package cl.salmontt.app;

import cl.salmontt.data.GestorEntidades;
import cl.salmontt.model.entities.*;
import cl.salmontt.utils.TablaConFiltros;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.util.Objects;


public class Main {


    public static void main(String[] args) {

        //Mensaje de Bienvenida
        JOptionPane.showMessageDialog(null,

                "               . . . : : :  SalmonttApp  : : : . . .",
                "",
                JOptionPane.PLAIN_MESSAGE);

        //Se llama al gestor de entidades
        GestorEntidades gestor = new GestorEntidades();

        // Lista que contiene las entidades
        List<Registrable> entidad = gestor.ConsultarEntidades();


        // Cargar el logo desde recursos
        ImageIcon logo;
        logo = new ImageIcon(
                Objects.requireNonNull(Main.class.getResource("/icons/logo.JPG")));

        // Escalar si es muy grande (ej. 32x32 px)
        Image img = logo.getImage().getScaledInstance(128, 120, Image.SCALE_SMOOTH);
        ImageIcon logoEscalado = new ImageIcon(img);


        // Definición de Opciones para el Desplegable

        Object[] menuOpciones = {
                "Ingresar Centro de Cultivo",
                "Ingresar Planta de Proceso",
                "Ingresar Empleado",
                "Ingresar Proveedor",
                "Ingresar Producto",
                "Ingresar Orden",
                "Mostrar Datos"
        };

        // Condicion ciclo while
        boolean continuar = true;

        while (continuar) {

            // Mostrar input dialog usando el JComboBox (Desplegable)
            Object opcion = JOptionPane.showInputDialog(
                    null,                           // Componente padre
                    "Seleccione una opción:",       // Mensaje
                    "SalmonttApp",                  // Título
                    JOptionPane.PLAIN_MESSAGE,      // Tipo de mensaje
                    logoEscalado,                   // Ícono personalizado
                    menuOpciones,                   // Lista de opciones para el JComboBox
                    menuOpciones[6]                 // Opción inicial seleccionada
            );

            // Boton de salida
            if (Objects.isNull(opcion)) {
                int confirmar = JOptionPane.showConfirmDialog(null,
                        "¿Desea salir de la aplicación?",
                        "Seleccione una opción:", JOptionPane.YES_NO_OPTION);

                if (confirmar == JOptionPane.YES_OPTION) {
                    //mensaje de despedida
                    JOptionPane.showMessageDialog(null, ". . . : : :  Gracias por utilizar SalmonttApp.  : : : . . .", "", JOptionPane.PLAIN_MESSAGE);
                    continuar = false; // sale de la aplicacion
                } else {
                    continue;
                }
                break; // Con esto no pasa al switch case al cerrar la app.
            }

            String opcionSeleccionada = opcion.toString();
            switch (opcionSeleccionada) {
                case ("Ingresar Centro de Cultivo"): //Agregar Centro de Cultivo

                    // Mensaje en pantalla para solicitar información
                    JOptionPane.showMessageDialog(null, "Ingresar Centro de Cultivo", "SalmonttApp", JOptionPane.INFORMATION_MESSAGE);
                    String nombreCC = JOptionPane.showInputDialog(null, "Nombre: ", "Centro de Cultivo", JOptionPane.PLAIN_MESSAGE);  // Agregar Nombre
                    if (nombreCC==null){
                        break;
                    }
                    String comunaCC = JOptionPane.showInputDialog(null, "Comuna: ", "Centro de Cultivo", JOptionPane.PLAIN_MESSAGE);  // Agregar Comuna
                    if (comunaCC==null){
                        break;
                    }
                    try {
                        int produccion = Integer.parseInt(JOptionPane.showInputDialog(null, "Toneladas producidas: ", "Centro de Cultivo", JOptionPane.PLAIN_MESSAGE));

                        // Valida que no tenga valores vacíos y sean positivos
                        if (!nombreCC.trim().equals("") && !comunaCC.trim().equals("") && produccion > 0) {
                            // Se agrega el centro de cultivo a la lista de entidades
                            gestor.agregarEntidade(new CentroCultivo(nombreCC, comunaCC, produccion));
                            gestor.guardarCambios();
                            JOptionPane.showMessageDialog(null, "Centro de Cultivo agregado exitosamente.\n", "SalmonttApp", JOptionPane.PLAIN_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.\n", "Error", JOptionPane.WARNING_MESSAGE);
                        }

                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                case ("Ingresar Planta de Proceso") : //Agregar Planta de Proceso
                    JOptionPane.showMessageDialog(null, "Ingresar Planta de Proceso", "SalmonttApp", JOptionPane.INFORMATION_MESSAGE);
                    String nombrePP = JOptionPane.showInputDialog(null, "Nombre: ", "Planta de Proceso", JOptionPane.PLAIN_MESSAGE);
                    if (nombrePP==null){
                        break;
                    }
                    String comunaPP = JOptionPane.showInputDialog(null, "Comuna: ", "Planta de Proceso", JOptionPane.PLAIN_MESSAGE);
                    if (comunaPP==null){
                        break;
                    }
                    try {
                        int produccionPP = Integer.parseInt(JOptionPane.showInputDialog(null, "Toneladas por mes procesadas: ", "Planta de Proceso", JOptionPane.PLAIN_MESSAGE));
                        // Valida que no tenga valores vacíos y sean positivos
                        if (!nombrePP.trim().equals("") && !comunaPP.trim().equals("") && produccionPP > 0) {
                            // Se agrega el centro de cultivo a la lista de entidades
                            gestor.agregarEntidade(new PlantaProceso(nombrePP, comunaPP, produccionPP));
                            //Se guardan los cambios en el archivo
                            gestor.guardarCambios();
                            JOptionPane.showMessageDialog(null, "Planta de Proceso agregada exitosamente.\n", "SalmonttApp", JOptionPane.PLAIN_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.\n", "Error", JOptionPane.WARNING_MESSAGE);
                        }

                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                case ("Ingresar Empleado") : //Agregar Empleado
                    JOptionPane.showMessageDialog(null, "Ingresar Empleado", "SalmonttApp", JOptionPane.INFORMATION_MESSAGE);
                    String nombreE = JOptionPane.showInputDialog(null, "Nombre: ", "Empleado", JOptionPane.PLAIN_MESSAGE);
                    if (nombreE==null){
                        break;
                    }
                    String cargoE = JOptionPane.showInputDialog(null, "Cargo: ", "Empleado", JOptionPane.PLAIN_MESSAGE);
                    if (cargoE==null){
                        break;
                    }
                    String correoE = JOptionPane.showInputDialog(null, "Correo: ", "Empleado", JOptionPane.PLAIN_MESSAGE);
                    if (correoE==null){
                        break;
                    }
                    // Valida que no tenga valores vacíos
                    if (!nombreE.trim().equals("") && !cargoE.trim().equals("") && !correoE.trim().equals("")) {
                        // Se agrega el centro de cultivo a la lista de entidades
                        gestor.agregarEntidade(new Empleado(nombreE, cargoE, correoE));
                        //Se guardan los cambios en el archivo
                        gestor.guardarCambios();
                        JOptionPane.showMessageDialog(null, "Empleado agregado exitosamente.\n", "SalmonttApp", JOptionPane.PLAIN_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.\n", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                    case ("Ingresar Proveedor") : //Agregar Proveedor
                    JOptionPane.showMessageDialog(null, "Ingresar Proveedor", "SalmonttApp", JOptionPane.INFORMATION_MESSAGE);
                    String nombreP = JOptionPane.showInputDialog(null, "Nombre: ", "Proveedor", JOptionPane.PLAIN_MESSAGE);
                        if (nombreP==null){
                            break;
                        }
                    String rubroP = JOptionPane.showInputDialog(null, "Rubro: ", "Proveedor", JOptionPane.PLAIN_MESSAGE);
                        if (rubroP==null){
                            break;
                        }
                    String correoP = JOptionPane.showInputDialog(null, "Correo: ", "Proveedor", JOptionPane.PLAIN_MESSAGE);
                        if (correoP==null){
                            break;
                        }
                    if (!nombreP.trim().equals("") && !rubroP.trim().equals("") && !correoP.trim().equals("")) {
                        // Se agrega el centro de cultivo a la lista de entidades
                        gestor.agregarEntidade(new Proveedor(nombreP, rubroP, correoP));
                        //Se guardan los cambios en el archivo
                        gestor.guardarCambios();
                        JOptionPane.showMessageDialog(null, "Proveedor agregado exitosamente.\n", "SalmonttApp", JOptionPane.PLAIN_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.\n", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                    case ("Ingresar Producto") :
                        JOptionPane.showMessageDialog(null, "Ingresar Producto", "SalmonttApp", JOptionPane.INFORMATION_MESSAGE);
                        String codigoProducto = JOptionPane.showInputDialog(null, "Codigo: ", "Producto", JOptionPane.PLAIN_MESSAGE);
                        if (codigoProducto==null){
                            break;
                        }
                        String nombreProducto = JOptionPane.showInputDialog(null, "Nombre: ", "Producto", JOptionPane.PLAIN_MESSAGE);
                        if (nombreProducto==null){
                            break;
                        }
                        Double precioProducto = Double.parseDouble(JOptionPane.showInputDialog(null, "Precio: ", "Producto", JOptionPane.PLAIN_MESSAGE));

                        int stockProducto = Integer.parseInt(JOptionPane.showInputDialog(null, "Stock: ", "Producto", JOptionPane.PLAIN_MESSAGE));

                        if (!codigoProducto.trim().equals("") && !nombreProducto.trim().equals("") && precioProducto>0 && stockProducto>0) {
                            // Se agrega el centro de cultivo a la lista de entidades
                            gestor.agregarEntidade(new Producto(codigoProducto, nombreProducto, precioProducto, stockProducto));
                            //Se guardan los cambios en el archivo
                            gestor.guardarCambios();
                            JOptionPane.showMessageDialog(null, "Producto agregado exitosamente.\n", "SalmonttApp", JOptionPane.PLAIN_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.\n", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                        break;

                    case ("Ingresar Orden"):
                        // Usamos el metodo de validación que filtra los datos actuales del gestor
                        // Este metodo debe retornar un objeto OrdenDeCompra
                        Registrable nuevaOrden = cl.salmontt.utils.Validaciones.validarOrdenDeCompra(entidad);

                        if (nuevaOrden != null) {
                            gestor.agregarEntidade(nuevaOrden);
                            gestor.guardarCambios();
                            JOptionPane.showMessageDialog(null, "Orden de Compra generada y guardada.", "SalmonttApp", JOptionPane.PLAIN_MESSAGE);
                        }
                    break;

                    case ("Mostrar Datos") :// Llamada al metodo que ahora tiene los títulos dinámicos
                    TablaConFiltros.mostrarTabla(entidad);
                    break;


                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
