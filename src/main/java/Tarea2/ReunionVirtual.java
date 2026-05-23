package Tarea2;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * Permite la creacion de una Reunion de tipo virtual, hereda los atributos y comportamientos
 * de la clase Reunion y anade un enlace.
 */
public class ReunionVirtual extends Reunion {
    private String enlace;


    /**
     * Constructor que inicializa los datos de la reunion y las listas de control de asistencia.
     * @param fecha Dia en el cual se va a realizar la reunion.
     * @param horaPrevista Hora en la cual se va a realizar la reunion.
     * @param duracionPrevista Tiempo estimado de duracion de la reunion.
     * @param organizador Empleado que organiza la reunion.
     * @param enlace Link para unirse a la reunion.
     */
    //se modofico agregando string enlace al constructor para que guarde el dato real
    public ReunionVirtual(tipoReunion tipo, Date fecha, Instant horaPrevista, Duration duracionPrevista, Empleado organizador, String enlace){
        super(tipo,fecha, horaPrevista, duracionPrevista, organizador);
        this.enlace=enlace;
    }
    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    @Override
    public String obtenerTipoOEnlace() {
        return "Virtual - Enlace: " + (enlace != null ? enlace : "No asignado");
    }
}
