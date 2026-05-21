package Tarea2;
import java.time.Instant;

public class Invitacion {
    private Instant hora;
    private Empleado empleado;
    /**
     * construye una invitacion asociando a un empleado y registrando la hora actual
     * @param empleado el empleado al cual se le invita
     */
    public Invitacion(Empleado empleado) {
        this.empleado=empleado;
        this.hora=Instant.now();//guarda automaticamente el momento exacto en que se crea
    }
    /**
     * construye una invitacion con una hora personalizada (para planificaciones previas)
     * @param hora momento exacto para la invitacion
     */
    public Invitacion(Empleado empleado,Instant hora) {
        this.empleado=empleado;
        this.hora=hora;
    }
    //getters y stters
    public Empleado getEmpleado() {
        return empleado;
    }
    public Instant getHora() {
        return hora;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado=empleado;
    }
    public void setHora(Instant hora) {
        this.hora=hora;
    }
    @Override
    public String toString() {
        return "Invitacion -Hora = "+hora +", Enviada a = "+(empleado!=null?empleado.getNombre()+" "+empleado.getApellidos() : "Nadie")+" -";
    }
}