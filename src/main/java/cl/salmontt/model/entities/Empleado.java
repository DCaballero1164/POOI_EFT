package cl.salmontt.model.entities;

import cl.salmontt.utils.Direccion;
import cl.salmontt.utils.Rut;

public class Empleado extends Persona {

    private String cargo;
    private String correo;

    public Empleado(String nombre, String cargo, String correo) {
        super(nombre);
        this.cargo = cargo;
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public String getCorreo() {
        return correo;
    }


    @Override
    public String mostrarResumen() {
        return String.format(" %-20s | %-20s | %-20s ", getNombre(), getCargo(), getCorreo());

    }

}
