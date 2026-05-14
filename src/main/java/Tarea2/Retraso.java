package Tarea2;

import java.time.Instant;

/**
 *
 */
public class Retraso extends Asistencia {

    private Instant hora;

    /**
     * Construye un registro de los retrasos de los empleados a la reunion.
     * @param empleado Objeto que represneta al empleado.
     * @param hora Hora en la que ingresa el empleado a la reunion.
     */
    public Retraso (Empleado empleado, Instant hora) {
        super(empleado);
        this.hora = hora;
    }


    public Instant getHora() {
        return hora;
    }

    public void setHora(Instant hora) {
        this.hora = hora;
    }
}
