package cl.salmontt.utils;

public class Rut {
    private String numero;
    private char dv;

    public Rut(String rutCompleto) {
        // Limpia puntos y guiones si vienen en el string
        String limpio = rutCompleto.replace(".", "").replace("-", "").toUpperCase();
        if (limpio.length() < 2) {
            this.numero = "0";
            this.dv = '0';
        } else {
            this.dv = limpio.charAt(limpio.length() - 1);
            this.numero = limpio.substring(0, limpio.length() - 1);
        }
    }

    public boolean esValido() {
        try {
            // Intentamos convertir y validar
            return Validaciones.validarRut(Integer.parseInt(numero), dv);
        } catch (NumberFormatException e) {
            // Si el número tiene letras o símbolos, el RUT no es válido
            System.err.println("Error: El número de RUT contiene caracteres no válidos.");
            return false;
        }
    }

    @Override
    public String toString() {
        return numero + "-" + dv;
    }
}