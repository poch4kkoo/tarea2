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
    private Empleado organizador;
    private Empleado emp1;
    private Empleado emp2;
    private Empleado emp3;

    // Tiempos para las pruebas
    private Instant horaInicio = Instant.now();
    private Instant horaFin = horaInicio.plus(Duration.ofMinutes(45));
    private Instant minAtraso = horaInicio.plus(Duration.ofMinutes(10));

    // Antes de ejecutar cada test, le presentamos estas condiciones iniciales.
    @BeforeEach
    void setUp() {

        organizador = new Empleado("000", "Sinson", "Marge", "ms@udec.cl");

        // Inicializamos una reunión virtual
        reunion = new ReunionVirtual(tipoReunion.TECNICA, new Date(), horaInicio, Duration.ofMinutes(60), organizador,"www.enlace.com");

        // Creamos 3 empleados
        emp1 = new Empleado("123", "Sinson", "Homero", "hs@udec.cl");
        emp2 = new Empleado("456", "Sinson", "Bart", "bs@udec.cl");
        emp3 = new Empleado("789", "Sinson", "Lisa", "ls@udec.cl");

        reunion.getInvitaciones().add(new Invitacion(emp1, horaInicio));
        reunion.getInvitaciones().add(new Invitacion(emp2, horaInicio));
        reunion.getInvitaciones().add(new Invitacion(emp3, horaInicio));

    }

    @Test
    @DisplayName("Test para verificar lista de asistencias")
    void obtenerAsistencias() {
        Asistencia asistenciaHomero = new Asistencia(emp1);
        Retraso retrasoBart = new Retraso(emp2, minAtraso);

        reunion.getAsistencias().add(asistenciaHomero);
        reunion.getAsistencias().add(retrasoBart);

        List<Asistencia> listaAsistencias = reunion.obtenerAsistencias();

        // Comprobamos que ambos están guardados en la lista de asistencia
        assertEquals(2, listaAsistencias.size(), "Deberían haber exactamente 2 registros en asistencias");
        assertTrue(listaAsistencias.contains(asistenciaHomero), "asistencia de Homero");
        assertTrue(listaAsistencias.contains(retrasoBart), "asistencia de Bart");
    }

    @Test
    @DisplayName("Test para verificar lista de retrasos")
    void obtenerRetrasos() {
        Asistencia asistenciaHomero = new Asistencia(emp1);
        Retraso retrasoBart = new Retraso(emp2, horaInicio.plus(Duration.ofMinutes(10))); // Subclase Retraso

        reunion.getAsistencias().add(asistenciaHomero);
        reunion.getAsistencias().add(retrasoBart);


        List<Asistencia> listaRetrasos = reunion.obtenerRetrasos();

        assertEquals(1, listaRetrasos.size(), "Debería haber 1 persona en la lista de retrasos");
        assertEquals(emp2, listaRetrasos.getFirst().getPersona(), "El empleado atrasado deberia ser Bart");
    }

    @Test
    void obtenerTotalAsistencia() {

        reunion.getAsistencias().add(new Asistencia(emp1));
        reunion.getAsistencias().add(new Asistencia(emp2));

        assertEquals(2, reunion.obtenerTotalAsistencia(), "Debe ser 2");

    }

    @Test
    void obtenerPorcentajeAsistencia() {

        reunion.getAsistencias().add(new Asistencia(emp1));
        reunion.getAsistencias().add(new Retraso(emp2, minAtraso));


        float porcentaje = reunion.obtenerPorcentajeAsistencia();
        assertEquals(66.67f, porcentaje, 0.01f, "Asisten 2 de los 3 invitados, deberia dar 66.67%");
    }

    @Test
    @DisplayName("Caso Extremo: Lanzar excepción si se intenta agregar una nota a una reunión ya finalizada")
    void testExcepcionNotaEnReunionFinalizada() {

        reunion.iniciar();
        reunion.finalizar();

        Nota notaTardia = new Nota("Acuerdo de último minuto");


        assertThrows(ReunionFinalizadaException.class, () -> {
            reunion.agregarNota(notaTardia);
        }, "Debería rechazar la nota porque la reunión ya se cerró");
    }
}