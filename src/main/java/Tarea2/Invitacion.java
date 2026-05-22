package Tarea2;
import java.time.Instant;

public class Invitacion {
    private Instant hora;
    private Persona persona;
    /**
     * construye una invitacion asociando a un empleado y registrando la hora actual
     * @param persona el empleado al cual se le invita
     */
    public Invitacion(Persona persona) {
        this.persona = persona;
        this.hora = Instant.now();//guarda automaticamente el momento exacto en que se crea
    }

    /**
     * Construye una invitacion con una hora personalizada (para planificaciones previas)
     * @param hora momento exacto para la invitacion.
     * @param persona persona a la que se invita (puede ser empleado o alguien externo).
     */
    public Invitacion(Persona persona,Instant hora) {
        this.persona = persona;
        this.hora=hora;
    }
    //getters y stters
    public Persona getPersona() {
        return persona;
    }
    public Instant getHora() {
        return hora;
    }
    public void setPersona (Persona persona) {
        this.persona = persona;
    }
    public void setHora(Instant hora) {
        this.hora=hora;
    }

    @Override
    public String toString() {
        return "Invitacion -Hora = "+hora +", Enviada a = "+(persona!=null? persona.getNombre()+" "+persona.getApellidos() : "Nadie")+" -";
    }
}