package Tarea2;

import java.time.Instant;

/**
 * Representa un tipo especial de asistencia, en donde el empleado llega tarde.
 */
public class Retraso extends Asistencia {

    private Instant hora;

    /**
     * Construye un registro de los retrasos de los empleados a la reunion.
     * @param empleado Objeto que representa al empleado que llega tarde.
     * @param hora Hora  exacta en la que ingresa el empleado a la reunion.
     */
    public Retraso (Empleado empleado, Instant hora) {
        super(empleado);
        this.hora = hora;
    }

    //===========================================================================
    // Getters y Setters
    //===========================================================================
    public Instant getHora() {
        return hora;
    }

    public void setHora(Instant hora) {
        this.hora = hora;
    }

    /**
     * Genera una descripcion del retraso, indicando la hora de llegada.
     * @return Cadena de texto con la informacion del retraso.
     */
    @Override
    public String toString() {
        return super.toString() + "[RETRASO] Llego a las: " + hora.toString();
    }
}
