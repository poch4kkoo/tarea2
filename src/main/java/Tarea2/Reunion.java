package Tarea2;

import java.util.List;
import java.util.ArrayList;
import java.time.Instant;
import java.time.Duration;
import java.util.Date;

/**
 * Representa la estructura base de una reunion.
 * Centraliza la gestión de tiempos, invitados y el registro de asistencia.
 */
public abstract class Reunion {

    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;

    private List<Asistencia> asistencias;
    private List<Invitacion> invitaciones;

    /**
     * Constructor que inicializa los datos de la reunion y alas listas de control de asistencia.
     * @param fecha Dia en el cual se va a realizar la reunion.
     * @param horaPrevista Hora en la cual se va a realizar la reunion.
     * @param duracionPrevista Timepo estimado de duracion de la reunion.
     * @param horaInicio Registro real del inicio de una reunion.
     * @param horaFin Registro real del fin de una reunion.
     */
    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista, Instant horaInicio, Instant horaFin) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;


        this.asistencias = new ArrayList<>();
        this.invitaciones = new ArrayList<>();
    }

    /**
     * Obtiene la lista completa de empleados presentes en la reunion.
     * @return List de objetos Asistencia registrados.
     */
    public List<Asistencia> obtenerAsistencias() {
        return asistencias;
    }

    //Falta obtenerAusencias()

    /**
     * Filtra y retorna los empleados que presentaron retrasos al unirse a la reunion.
     * @return Lista de asistencia que corresponde a la subclase Retraso.
     */
    public List<Asistencia> obtenerRetrasos() {
        List<Asistencia> retrasos = new ArrayList<>();

        for(Asistencia asistente : asistencias) {
            if (asistente instanceof Retraso) {
                retrasos.add(asistente);
            }
        }

        return retrasos;
    }

    /**
     * Obtiene la cantidad Total de empleados pressentes en la reunion.
     * @return Un numero entero que corresponde a la cantidad total de asistentes de la reunion.
     */
    public int obtenerTotalAsistencia() {
        return asistencias.size();
    }

    /**
     * Obtiene el porcentaje total de asistentes reales en la reunion.
     * @return Un numero float que corresponde a el porcentaje de empleados presentes en la reunion.
     */
    public float obtenerPorcentajeAsistencia() {
        if (invitaciones.isEmpty()){
            return 0;
        } else {
            return ((float) asistencias.size() / invitaciones.size()) * 100;
        }
    }

    /**
     * Calcula la duracion total real de la reunion.
     * @return Un mumero float que representa la cantidad de minutos que duro la reunion.
     */
    public float calcularTiempoReal() {
        if (horaInicio == null || horaFin == null){
            return 0;
        }

        Duration duracion = Duration.between(horaInicio, horaFin);
        return (float) duracion.toSeconds() / 60;
    }

    /**
     * Guarda la hora exacta de inicio de una reunion.
     */
    public void iniciar() {
        horaInicio = Instant.now();
    }

    /**
     * Guarda la hora exacta de finalizacion de una reunion.
     */
    public void finalizar() {
        horaFin = Instant.now();
    }


    //===========================================================================
    // Getters y Setters
    //===========================================================================

    public Date getFecha() {
        return fecha;
    }

    public Instant getHoraPrevista() {
        return horaPrevista;
    }

    public Duration getDuracionPrevista() {
        return duracionPrevista;
    }

    public Instant getHoraInicio() {
        return horaInicio;
    }

    public Instant getHoraFin() {
        return horaFin;
    }

    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public List<Invitacion> getInvitaciones() {
        return invitaciones;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHoraPrevista(Instant horaPrevista) {
        this.horaPrevista = horaPrevista;
    }

    public void setDuracionPrevista(Duration duracionPrevista) {
        this.duracionPrevista = duracionPrevista;
    }

    public void setHoraInicio(Instant horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(Instant horaFin) {
        this.horaFin = horaFin;
    }

    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    public void setInvitaciones(List<Invitacion> invitaciones) {
        this.invitaciones = invitaciones;
    }


    /**
     * Devuelve una representación en cadena de la reunión.
     * Incluye los detalles de planificación, los registros de tiempo real
     * y un resumen del estado de asistencia.
     * @return Una cadena de texto con los atributos clave de la reunión.
     */
    @Override
    public String toString() {
        return "Reunion{ Fecha =" + fecha +
                ", horaPrevista = " + horaPrevista +
                ", horaInicio = " + horaInicio +
                ", horaFin = " + horaFin +
                ", Total Asistencia = " + obtenerTotalAsistencia() +
                ", Porcentaje Asistencia = " + obtenerPorcentajeAsistencia() +
                ", Retrasos = " + obtenerRetrasos() + " }";
    }
}
