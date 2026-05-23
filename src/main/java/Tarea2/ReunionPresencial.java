package Tarea2;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * Permite la creacion de una Reunion de tipo presencial, hereda los atributos y comportamientos
 * de la clase Reunion y anade una sala.
 */
public class ReunionPresencial extends Reunion {


    /**
     * La sala física donde sera la reunión.
     */
    private String sala;

    /**
     * Constructor que inicializa los datos de la reunion y las listas de control de asistencia.
     * @param fecha Dia en el cual se va a realizar la reunion.
     * @param horaPrevista Hora en la cual se va a realizar la reunion.
     * @param duracionPrevista Tiempo estimado de duracion de la reunion.
     * @param organizador Empleado que organiza la reunion.
     * @param sala Nombre de la sala asignada para la reunion.
     */
    public ReunionPresencial(tipoReunion tipo,Date fecha, Instant horaPrevista, Duration duracionPrevista, Empleado organizador, String sala){
        super(tipo,fecha, horaPrevista, duracionPrevista, organizador);
        this.sala = sala;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    @Override
    public String obtenerTipoOEnlace() {
        return "Presencial - Sala: " +(sala!=null ? sala :"No asignada");
    }
}
