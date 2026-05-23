package Tarea2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class ReunionMasInformeTest {

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

        reunion.getInvitaciones().add(new Invitacion(organizador, horaInicio));
        reunion.getInvitaciones().add(new Invitacion(emp2, horaInicio));
        reunion.getInvitaciones().add(new Invitacion(emp3, horaInicio));

        // Homero llega a la hora
        // Bart llega 15 min tarde
        // Lisa no asiste (no la agregamos a las asistencias)
        reunion.getAsistencias().add(new Asistencia(organizador));
        reunion.getAsistencias().add(new Retraso(emp2, Instant.now().plus(Duration.ofMinutes(15))));

        assertEquals(3, reunion.getInvitaciones().size(), "Deben haberse registrado 3 invitaciones.");
    }

    @Test
    @DisplayName("Debería calcular correctamente el total de asistencias y retrasos")
    void testRegistroAsistenciaYRetrasos() {


        // Verificaciones de asistencia
        assertEquals(2, reunion.obtenerTotalAsistencia(), "El total de personas que asistieron debe ser 2.");
        assertEquals(1, reunion.obtenerRetrasos().size(), "Debe haber exactamente 1 retraso registrado (Bart).");
    }

    @Test
    @DisplayName("Generacion del informe")
    void testGeneracionInformeTxt() {
        String nombreInforme = "ReunionInformeTest.txt";

        reunion.iniciar();

        // Simulamos una duracion de 45 min
        reunion.setHoraInicio(horaInicio.minus(Duration.ofMinutes(45)));
        reunion.finalizar();

        // Ejecutamos la acción de generar el informe
        reunion.generarInformeTxt(nombreInforme);

        File archivoResultado = new File(nombreInforme);

        // Verificaciones del archivo
        assertTrue(archivoResultado.exists(), "El archivo de informe .txt debió haberse creado.");
        assertTrue(archivoResultado.length() > 0, "El archivo de informe debería contener texto y no estar vacío.");


    }
}