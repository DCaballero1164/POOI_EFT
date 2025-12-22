package cl.salmontt.model.entities;

public class CentroCultivo extends UnidadOperativa {

    //Atributos de la subclase
    private Integer toneladasProduccion;

    //Constructor de la subclase
    public CentroCultivo(String nombre, String comuna, Integer toneladasProduccion) {
        super(nombre, comuna);
        this.toneladasProduccion = toneladasProduccion;
    }

    //Getter
    public Integer getToneladasProduccion() {
        return toneladasProduccion;
    }

    @Override
    public String mostrarResumen() {
        return String.format(" %-20s | %-20s | %d ", getNombre(), getComuna(), getToneladasProduccion());
    }

    //Metodo toString
    @Override
    public String toString() {
        return "CentroCultivo{" +
                "nombre='" + getNombre() + '\'' +
                ", comuna='" + getComuna() + '\'' +
                ", toneladasProduccion=" + getToneladasProduccion() +
                '}';
    }
}
