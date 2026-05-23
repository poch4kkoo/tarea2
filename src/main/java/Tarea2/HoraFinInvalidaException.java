package Tarea2;

/**
 * Excepcion que es lanzada en caso de que una reunion finalice antes
 * de su hora de inicio configurada.
 */
public class HoraFinInvalidaException extends RuntimeException {
    public HoraFinInvalidaException(String message) {
        super(message);
    }
}
