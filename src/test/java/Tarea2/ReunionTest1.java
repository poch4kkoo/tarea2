package Tarea2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReunionTest1 {

    // Variables globales para el escenario de prueba
    private Reunion reunion;
    private Empleado empNormal;
    private Empleado empAtrasado;
    private Empleado empAusente;
    Instant horaInicio = Instant.now();

    // Antes de ejecutar cada test, le presentamos estas condiciones iniciales.
    @BeforeEach
    void setUp() {
        // Inicializamos una reunión virtual
        reunion = new ReunionVirtual(tipoReunion.TECNICA, new Date(), horaInicio, Duration.ofMinutes(60), horaInicio, horaInicio.plus(Duration.ofMinutes(45)), "www.enlace.com");

        // Creamos 3 empleados
        empNormal = new Empleado("123", "Sinson", "Homero", "hs@udec.cl");
        empAtrasado = new Empleado("456", "Sinson", "Bart", "bs@udec.cl");
        empAusente = new Empleado("789", "Sinson", "Lisa", "ls@udec.cl");

    }

    @Test
    @DisplayName("Test de asistencias")
    void obtenerAsistencias() {
        Asistencia asistenciaHomero = new Asistencia(empNormal);
        Retraso retrasoBart = new Retraso(empAtrasado, Instant.now());

        reunion.getAsistencias().add(asistenciaHomero);
        reunion.getAsistencias().add(retrasoBart);

        List<Asistencia> listaAsistencias = reunion.obtenerAsistencias();

        // Comprobamos que ambos están guardados en la lista de asistencia
        assertNotNull(listaAsistencias, "La lista de asistencias no debería ser nula");
        assertEquals(2, listaAsistencias.size(), "Deberían haber exactamente 2 registros en asistencias");
        assertTrue(listaAsistencias.contains(asistenciaHomero), "Debería incluir la asistencia de Homero");
        assertTrue(listaAsistencias.contains(retrasoBart), "Debería incluir la asistencia de Bart");
    }

    @Test
    @DisplayName("Test de retrasos")
    void obtenerRetrasos() {
        Asistencia asistenciaHomero = new Asistencia(empNormal);
        Retraso retrasoBart = new Retraso(empAtrasado, Instant.now()); // Subclase Retraso

        reunion.getAsistencias().add(asistenciaHomero);
        reunion.getAsistencias().add(retrasoBart);


        List<Asistencia> listaRetrasos = reunion.obtenerRetrasos();

        assertEquals(1, listaRetrasos.size(), "Debería haber 1 persona en la lista de retrasos");
        assertEquals(empAtrasado, listaRetrasos.getFirst().getEmpleado(), "El empleado atrasado deberia ser Bart");
    }

    @Test
    void obtenerTotalAsistencia() {
    }

    @Test
    void obtenerPorcentajeAsistencia() {
    }
}