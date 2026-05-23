package Tarea2;

/**
 * Excepcion que es lanzada en caso de intentar medir parametros
 * que solo se pueden obtener una vez la reunion haya finalizado.
 * Tambien cubre casos extremos como finalizar una reunion antes
 * de que esta haya empezado.
 */
public class ReunionNoIniciadaException extends RuntimeException {
    public ReunionNoIniciadaException(String message) {
        super(message);
    }
}
