package Tarea2;

/**
 * Excepcion que es lanzada en caso de que se intente hacer un cambio
 * en la reunion una vez que esta haya finalizado.
 */
public class ReunionFinalizadaException extends RuntimeException {
    public ReunionFinalizadaException(String message) {
        super(message);
    }
}
