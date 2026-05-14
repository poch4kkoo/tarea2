package Tarea2;

import java.util.List;
import java.util.ArrayList;
import java.time.Instant;
import java.time.Duration;
import java.util.Date;

/**
 * Clase que representa
 */
public abstract class Reunion {

    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;

    private List<Asistencia> asistencias;
    private List<Invitacion> invitaciones;


    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista, Instant horaInicio, Instant horaFin) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;

        this.asistencias = new ArrayList<>();
        this.invitaciones = new ArrayList<>();
    }

    public List<Asistencia> obtenerAsistencias() {
        return asistencias;
    }

    public List<Asistencia> obtenerRetrasos() {
        List<Asistencia> retrasos = new ArrayList<>();

        for(Asistencia asistente : asistencias) {
            if (asistente instanceof Retraso) {
                retrasos.add(asistente);
            }
        }

        return retrasos;
    }

    public int obtenerTotalAsistencia() {
        return asistencias.size();
    }

    public float obtenerPorcentajeAsistencia() {
        if (invitaciones.isEmpty()){
            return 0;
        } else {
            return (float) (asistencias.size() / invitaciones.size()) * 100;
        }
    }

    public float calcularTiempoReal() {
        if (horaInicio == null || horaFin == null){
            return 0;
        }

        Duration duracion = Duration.between(horaInicio, horaFin);
        return (float) duracion.toSeconds() / 60;
    }

    public void iniciar() {
        horaInicio = Instant.now();
    }

    public void finalizar() {
        horaFin = Instant.now();
    }

}
