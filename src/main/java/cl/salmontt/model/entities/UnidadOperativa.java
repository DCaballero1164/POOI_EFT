package cl.salmontt.model.entities;

public abstract class UnidadOperativa implements Registrable {

    //Se definen los atributos de la superclase abstracta
    private String nombre;
    private String comuna;

    //Costructor de la superclase UnidadOperativa
    public UnidadOperativa(String nombre, String comuna) {
        this.nombre = nombre;
        this.comuna = comuna;
    }

    //Getter
    public String getNombre() {
        return nombre;
    }

    public String getComuna() {
        return comuna;
    }

    public abstract String mostrarResumen();

    //Metodo toString
    public String toString() {
        return "UnidadOperativa{" +
                "nombre='" + nombre + '\'' +
                ", comuna='" + comuna + '\'' +
                '}';
    }
}
