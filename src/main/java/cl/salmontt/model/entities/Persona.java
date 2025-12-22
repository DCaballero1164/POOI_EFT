package cl.salmontt.model.entities;

public abstract class Persona implements Registrable {
    private String nombre;

    //Constructor de la clase abstracta Persona
    public Persona(String nombre) {
        this.nombre = nombre;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }

    //Metodo abstracto mostrar resumen
    public abstract String mostrarResumen();

    //Metodo toString
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + getNombre() + '\'' +
                '}';
    }
}
