package cl.salmontt.utils;

import java.time.YearMonth;

public class Tarjeta {

    enum TipoTarjeta { DEBITO, CREDITO, PREPAGO }

    private String numeroEnmascarado; // Ej: **** **** **** 1234
    private String titular;
    private YearMonth fechaExpiracion;
    private TipoTarjeta tipo;

    public Tarjeta(String numeroCompleto, String titular, YearMonth fechaExpiracion, TipoTarjeta tipo) {
        if (numeroCompleto == null || numeroCompleto.length() < 12) {
            throw new Excepciones.TarjetaInvalidaException("Número de tarjeta inválido");
        }
        this.numeroEnmascarado = enmascarar(numeroCompleto);
        this.titular = titular;
        this.fechaExpiracion = fechaExpiracion;
        this.tipo = tipo;
    }

    private String enmascarar(String numero) {
        String ultimos4 = numero.substring(numero.length() - 4);
        return "**** **** **** " + ultimos4;
    }

    public String getNumeroEnmascarado() { return numeroEnmascarado; }
    public String getTitular() { return titular; }
    public YearMonth getFechaExpiracion() { return fechaExpiracion; }
    public TipoTarjeta getTipo() { return tipo; }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "numero='" + numeroEnmascarado + '\'' +
                ", titular='" + titular + '\'' +
                ", expiracion=" + fechaExpiracion +
                ", tipo=" + tipo +
                '}';
    }
}