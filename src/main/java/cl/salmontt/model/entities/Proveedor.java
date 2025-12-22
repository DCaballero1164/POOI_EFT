package cl.salmontt.model.entities;

public class Proveedor extends Persona {

    private String rubro;
    private String correo;

    public Proveedor(String nombre, String rubro, String correo) {
        super(nombre);
        this.rubro = rubro;
        this.correo = correo;
    }

    public String getRubro() {
        return rubro;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String mostrarResumen() {
        return String.format(" %-20s | %-20s | %-20s ", getNombre(), getRubro(), getCorreo());
    }


}
