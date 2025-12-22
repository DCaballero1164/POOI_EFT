package cl.salmontt.utils;

public class Direccion {
    private String calle;
    private String numero;
    private String comuna;
    private String region;
    private String pais;

    public Direccion(String calle, String numero, String comuna, String region, String pais) {
        this.calle = calle;
        this.numero = numero;
        this.comuna = comuna;
        this.region = region;
        this.pais = pais;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getComuna() {
        return comuna;
    }

    public String getRegion() {
        return region;
    }

    public String getPais() {
        return pais;
    }


    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + getCalle() + '\'' +
                ", numero='" + getNumero() + '\'' +
                ", comuna='" + getComuna() + '\'' +
                ", region='" + getRegion() + '\'' +
                ", pais='" + getPais() + '\'' +
                '}';
    }
}
