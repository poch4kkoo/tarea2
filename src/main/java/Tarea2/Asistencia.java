package Tarea2;

/**
 * Registra y gestiona la asistencia de un empleado a la reunion.
 */
public class Asistencia {

    private Persona persona;

    /**
     * Construye un registro de asistencia relacionado a un empleado correspondiente.
     * @param persona un objeto persona que registra su asistencia.
     */
    public Asistencia(Persona persona) {
        this.persona = persona;
    }

    //===========================================================================
    // Getters y Setters
    //===========================================================================
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * Devuelve los datos del asistente.
     * @return una cadena con los datos del empleado.
     */
    @Override
    public String toString() {
        return "Asistente: " + persona.getNombre() + " " + persona.getApellidos();
    }
}
