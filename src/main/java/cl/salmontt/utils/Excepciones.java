package cl.salmontt.utils;

/**
 * Archivo centralizado de excepciones personalizadas para el dominio SalmonttApp.
 * Permite manejar validaciones de RUT, Tarjeta y reglas generales de negocio.
 */
public class Excepciones extends RuntimeException {

    // --- Excepción para RUT inválido ---
    public static class RutInvalidoException extends RuntimeException {
        public RutInvalidoException(String mensaje) {
            super(mensaje);
        }
    }

    // --- Excepción para Tarjeta inválida ---
    public static class TarjetaInvalidaException extends RuntimeException {
        public TarjetaInvalidaException(String mensaje) {
            super(mensaje);
        }
    }

    // --- Excepción para validaciones genéricas ---
    public static class ValidacionException extends RuntimeException {
        public ValidacionException(String mensaje) {
            super(mensaje);
        }
    }

    // --- Excepción para operaciones de negocio ---
    public static class OperacionNoPermitidaException extends RuntimeException {
        public OperacionNoPermitidaException(String mensaje) {
            super(mensaje);
        }
    }
}
