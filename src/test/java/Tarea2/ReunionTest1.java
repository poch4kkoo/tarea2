package Tarea2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

class ReunionTest1 {

    // Variables globales para el escenario de prueba
    private Reunion reunion;
    private Empleado empNormal;
    private Empleado empAtrasado;
    private Empleado empAusente;

    // Antes de ejecutar cada test, le presentamos estas condiciones iniciales.
    @BeforeEach
    void setUp() {
        // Inicializamos una reunión virtual
        reunion = new ReunionVirtual(new Date(), Instant.now(), Duration.ofMinutes(60), Instant.now(), Instant.queseyo, "www.enlace.com");

        // Creamos 3 empleados
        empNormal = new Empleado("123", "Sinson", "Homero", "hs@udec.cl");
        empAtrasado = new Empleado("456", "Sinson", "Bart", "bs@udec.cl");
        empAusente = new Empleado("789", "Sinson", "Lisa", "ls@udec.cl");

    }

    @Test
    void obtenerAsistencias() {
    }

    @Test
    void obtenerRetrasos() {
    }

    @Test
    void obtenerTotalAsistencia() {
    }

    @Test
    void obtenerPorcentajeAsistencia() {
    }
}