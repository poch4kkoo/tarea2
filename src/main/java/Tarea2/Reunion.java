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

    private Empleado organizador;
    private List<Asistencia> asistencias;
    private List<Invitacion> invitaciones;
    private List<Nota> notas;

    private tipoReunion tipo;

    /**
     * Constructor que inicializa los datos de la reunion y alas listas de control de asistencia.
     * @param fecha Dia en el cual se va a realizar la reunion.
     * @param horaPrevista Hora en la cual se va a realizar la reunion.
     * @param duracionPrevista Tiempo estimado de duracion de la reunion.
     * @param organizador Empleado que organiza la reunion.
     */
    public Reunion(tipoReunion tipo,Date fecha, Instant horaPrevista, Duration duracionPrevista, Empleado organizador) {
        this.tipo=tipo;
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.organizador = organizador;

        this.horaInicio = null;
        this.horaFin = null;

        this.asistencias = new ArrayList<>();
        this.invitaciones = new ArrayList<>();
        this.notas = new ArrayList<>();

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
        List<Invitacion> ausencias = new ArrayList<>();
        //recorremos invitacion realizada
        for (Invitacion invitacion : invitaciones) {

            boolean asistio = false;

            Persona invitado = invitacion.getPersona();

            if (invitado == null) { continue; }

            //revisamos si el empleado aparece en la lista de asistencias
            for (Asistencia asistencia : asistencias) {
                Persona asistente = asistencia.getPersona();

                if (asistente == null) { continue; }

                if (invitado instanceof Empleado && asistente instanceof Empleado) {
                    Empleado empInvitado = (Empleado) invitado;
                    Empleado empAsistente = (Empleado) asistente;

                    if (empInvitado.getId().equals(empAsistente.getId())) {
                        asistio = true;
                        break;
                    }
                }

                else if (invitado instanceof InvitadoExterno && asistente instanceof InvitadoExterno) {
                    if (invitado.getCorreo().equalsIgnoreCase(asistente.getCorreo())) {
                        asistio = true;
                        break;
                    }
                }
            }
            //si vemos todas las asistencias y nunca se volvio true, entonces esta ausente
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
        if (horaInicio == null){
            throw new ReunionNoIniciadaException("No se puede calcular el tiempo real: la reunion aun no ha iniciado");
        }

        if (horaFin == null) {
            Duration tiempoTranscurrido = Duration.between(horaInicio, Instant.now());
            return (float) tiempoTranscurrido.toSeconds() / 60;
        }

        Duration duracion = Duration.between(horaInicio, horaFin);
        return (float) duracion.toSeconds() / 60;
    }

    public void agregarNota(Nota nota) {
        if (horaFin != null) {
            throw  new ReunionFinalizadaException("No se pueden añadir notas a una reunion ya finalizada");
        }

        if (nota != null) {
            this.notas.add(nota);
        }
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

        if (this.horaInicio == null) {
            throw new ReunionNoIniciadaException("No se puede finalizar una reunion que aun no ha comenzado");
        }

        if (Instant.now().isBefore(this.horaInicio)) {
            throw new HoraFinInvalidaException("La hora de finalización no puede ser anterior a la hora de inicio.");
        }

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

    public Empleado getOrganizador() {
        return organizador;
    }

    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public List<Invitacion> getInvitaciones() {
        return invitaciones;
    }

    public List<Nota> getNotas() {
        return notas;
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

    public void setOrganizador(Empleado organizador) {
        this.organizador = organizador;
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

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    /**
     * Devuelve una representación en cadena de la reunión.
     * Incluye los detalles de planificación, los registros de tiempo real
     * y un resumen del estado de asistencia.
     * @return Una cadena de texto con los atributos clave de la reunión.
     */
    @Override
    public String toString() {
        return "Reunion{ " +
                " Organizador = " + organizador +
                ", Fecha =" + fecha +
                ", horaPrevista = " + horaPrevista +
                ", horaInicio = " + horaInicio +
                ", horaFin = " + horaFin +
                ", Total Asistencia = " + obtenerTotalAsistencia() +
                ", Porcentaje Asistencia = " + obtenerPorcentajeAsistencia() +
                ", Retrasos = " + obtenerRetrasos() +
                ", Notas de la reunion = " + notas+ "}";
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
            pw.println("Organizador           : " + (organizador != null ? organizador.getNombre() + " " + organizador.getApellidos() : "No asignado"));
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
            if (this.asistencias.isEmpty()) {
                pw.println("No se registran asistencias en esta reunion.");
            } else {
                for (Asistencia asistente : asistencias) {
                    pw.println("  • " + asistente.toString());
                }
            }

            pw.println("\n-----------------------------------------------------------------------");
            pw.println("DETALLE DE AUSENCIAS");
            pw.println("-----------------------------------------------------------------------");

            List<Invitacion> ausencias = obtenerAusencias();
            if (ausencias.isEmpty()) {
                pw.println("No hubo ausencias. Asistencia perfecta.");
            } else {
                for (Invitacion ausente : ausencias) {
                    Persona p = ausente.getPersona();

                    if (p != null) {

                        if (p instanceof Empleado) {
                            Empleado emp = (Empleado) p;
                            pw.println("  • [EMPLEADO AUSENTE] " + emp.getNombre() + emp.getApellidos() + " [Id: " + emp.getId() + ", Correo: " + emp.getCorreo() + "]");
                        }

                        else if (p instanceof InvitadoExterno) {
                            pw.println("  • [EXTERNO AUSENTE] " + p.getNombre() + p.getApellidos() + " [Correo: " + p.getCorreo() + "]");
                        }
                    }
                }
            }

            pw.println("\n-----------------------------------------------------------------------");
            pw.println("NOTAS DE LA REUNION (Cronologicas)");
            pw.println("-----------------------------------------------------------------------");
            if (notas.isEmpty()) {
                pw.println("No se añadieron notas en la reunion.");
            } else {
                int num = 1;

                for(Nota nota : notas) {
                    pw.println("Nota #" + num + ": " + nota.toString());
                    num++;
                }
            }

            pw.println("=======================================================================");
            System.out.println("Informe generado con exito en el archivo: " +nombreArchivo );
        } catch (java.io.IOException e) {
            System.err.println("Error al escribir el archivo de informe: " +e.getMessage());
        }
    }
}
