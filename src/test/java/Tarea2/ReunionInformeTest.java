package Tarea2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class ReunionInformeTest {

    private Reunion reunion;
    private Empleado organizador;
    private Empleado emp2;
    private Empleado emp3;
    private Instant horaInicio;

    @BeforeEach
    void setUp() {
        // Inicializamos los datos antes de cada test
        horaInicio = Instant.now();
        organizador = new Empleado("E1", "Simpson", "Homero", "hs@udec.cl");
        emp2 = new Empleado("E2", "Simpson", "Bart", "bs@udec.cl");
        emp3 = new Empleado("E3", "Simpson", "Lisa", "ls@udec.cl");

        reunion = new ReunionVirtual(tipoReunion.TECNICA,
                new Date(), horaInicio, Duration.ofMinutes(60),
                organizador, "www.enlace.com");
    }

    @Test
    @DisplayName("Debería registrar correctamente las invitaciones a la reunión")
    void testInvitacionesReunion() {
        reunion.getInvitaciones().add(new Invitacion(organizador, horaInicio));
        reunion.getInvitaciones().add(new Invitacion(emp2, horaInicio));
        reunion.getInvitaciones().add(new Invitacion(emp3, horaInicio));

        assertEquals(3, reunion.getInvitaciones().size(), "Deben haberse registrado 3 invitaciones.");
    }

    @Test
    @DisplayName("Debería calcular correctamente el total de asistencias y retrasos")
    void testRegistroAsistenciaYRetrasos() {
        // Homero llega a la hora
        reunion.getAsistencias().add(new Asistencia(organizador));

        // Bart llega 15 minutos tarde
        Instant horaRetrasoBart = horaInicio.plus(Duration.ofMinutes(15));
        reunion.getAsistencias().add(new Retraso(emp2, horaRetrasoBart));

        // Lisa no asiste (no la agregamos a las asistencias)

        // Verificaciones de asistencia
        assertEquals(2, reunion.obtenerTotalAsistencia(), "El total de personas que asistieron o llegaron tarde debe ser 2.");
        assertEquals(1, reunion.obtenerRetrasos().size(), "Debe haber exactamente 1 retraso registrado (Bart).");
    }



    @Test
    @DisplayName("Debería generar un archivo de informe de texto no vacío")
    void testGeneracionInformeTxt() {
        String nombreInforme = "ReunionInformeTest.txt";

        reunion.iniciar();

        // Ejecutamos la acción de generar el informe
        reunion.generarInformeTxt(nombreInforme);

        File archivoResultado = new File(nombreInforme);

        // Verificaciones del archivo
        assertTrue(archivoResultado.exists(), "El archivo de informe .txt debió haberse creado.");
        assertTrue(archivoResultado.length() > 0, "El archivo de informe debería contener texto y no estar vacío.");

        reunion.finalizar();
    }
}