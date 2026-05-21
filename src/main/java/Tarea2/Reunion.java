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

    private tipoReunion tipo;
    /**
     * Constructor que inicializa los datos de la reunion y alas listas de control de asistencia.
     * @param fecha Dia en el cual se va a realizar la reunion.
     * @param horaPrevista Hora en la cual se va a realizar la reunion.
     * @param duracionPrevista Timepo estimado de duracion de la reunion.
     * @param horaInicio Registro real del inicio de una reunion.
     * @param horaFin Registro real del fin de una reunion.
     */
    public Reunion(tipoReunion tipo,Date fecha, Instant horaPrevista, Duration duracionPrevista, Instant horaInicio, Instant horaFin) {
        this.tipo=tipo;
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

    /**
     * obtiene la lista de invitaciones de las personas que no asistieron a la reunion
     * @return una lista con las invitaciones de los usuarios ausentes
     */
    public List<Invitacion> obtenerAusencias() {
        List<Invitacion> ausencias=new ArrayList<>();
        //recorremos invitacion realizada
        for (Invitacion invitacion : invitaciones) {
            boolean asistio=false;
            //revisamos si el empleado aparece en la lista de asistencias
            for (Asistencia asistencia : asistencias) {
                //comparamos el invitado con el empleado que asistio
                if (invitacion.getEmpleado()!=null && invitacion.getEmpleado().equals(asistencia.getEmpleado())) {
                    asistio=true;
                    break; //si ya encontramos que asistio,dejamos de buscar
                }
            }
            //sii vemos todas las asistencias y nunca se volvio true, entonces esta ausente
            if (!asistio) {
                ausencias.add(invitacion);
            }
        }
        return ausencias;
    }
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
    public tipoReunion getTipo() {
        return tipo;
    }

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

    public void setTipo(tipoReunion tipo) {
        this.tipo = tipo;
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
    // Métodos Nuevos (Requerimiento de Informe Txt)
     //Metodo abstracto que será implementado por las subclases virtuales/presenciales para identificar el lugar o enlace de la reu
    public abstract String obtenerTipoOEnlace();
    /**
     * genera un informe detallado de la reunion y lo guarda fisicamente en un archivo .txt
     * @param nombreArchivo nombre del archivo a crear (ejemplo:"informe_reunion.txt")
     */
    public void generarInformeTxt(String nombreArchivo) {
        try (java.io.FileWriter fw=new java.io.FileWriter(nombreArchivo);
             java.io.PrintWriter pw=new java.io.PrintWriter(fw)) {
// el signo de pregunta es un condicional porsia
            pw.println("=======================================================================");
            pw.println("                           INFORME DE REUNION                          ");
            pw.println("=======================================================================");
            pw.println("Fecha Planificada     : " + (fecha!=null?fecha.toString() : "No definida"));
            pw.println("Tipo de Reunión       : " + (tipo!=null?tipo.toString() : "No definido"));
            pw.println("Hora Prevista         : " + (horaPrevista!=null?horaPrevista.toString() : "No definida"));
            pw.println("Duracion Prevista     : " + (duracionPrevista!=null?duracionPrevista.toMinutes()+" minutos" : "No definida"));
            pw.println("Ubicacion / Conexion  : " + obtenerTipoOEnlace());

            pw.println("\n-----------------------------------------------------------------------");
            pw.println("TIEMPOS REALES DE EJECUCION");
            pw.println("-----------------------------------------------------------------------");
            pw.println("Hora de Inicio Real   : " + (horaInicio!=null?horaInicio.toString() : "No iniciada"));
            pw.println("Hora de Termino Real  : " + (horaFin!=null?horaFin.toString() : "No finalizada"));
            pw.println("Duracion Total Real   : " + calcularTiempoReal()+" minutos");

            pw.println("\n-----------------------------------------------------------------------");
            pw.println("ESTADISTICAS DE ASISTENCIA");
            pw.println("-----------------------------------------------------------------------");
            pw.println("Total Invitados       : " + invitaciones.size());
            pw.println("Asistentes Presentes  : " + obtenerTotalAsistencia());
            pw.println("Porcentaje Asistencia : " + String.format("%.2f",obtenerPorcentajeAsistencia())+"%");

            pw.println("\n-----------------------------------------------------------------------");
            pw.println("DETALLE DE ASISTENTES");
            pw.println("-----------------------------------------------------------------------");
            if (asistencias.isEmpty()) {
                pw.println("No se registran asistencias en esta reunion.");
            } else {
                for (Asistencia asistente : asistencias) {
                    pw.println("  • " + asistente.toString());
                }
            }
            System.out.println("Informe generado con exito en el archivo: " +nombreArchivo );
        } catch (java.io.IOException e) {
            System.err.println("Error al escribir el archivo de informe: " +e.getMessage());
        }
    }
}
