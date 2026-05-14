package Tarea2;

/**
 * Registra y gestiona la asistencia de un empleado a la reunion.
 */
public class Asistencia {

    private Empleado empleado;

    /**
     * Construye un registro de asistencia relacionado a un empleado correspondiente.
     * @param empleado un objeto empleado que registra su asistencia.
     */
    public Asistencia(Empleado empleado) {
        this.empleado = empleado;
    }

    //===========================================================================
    // Getters y Setters
    //===========================================================================
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * Devuelve los datos del asistente.
     * @return una cadena con los datos del empleado.
     */
    @Override
    public String toString() {
        return "Asistente: " + empleado.getNombre() + " " + empleado.getApellidos();
    }
}
