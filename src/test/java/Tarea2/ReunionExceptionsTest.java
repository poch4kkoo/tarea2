package Tarea2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class ReunionExceptionsTest {

    // Variables globales para el escenario de prueba
    private Reunion reunion;
    private Empleado organizador;

    // Tiempos para las pruebas
    private Instant horaInicio = Instant.now();
    private Instant horaFin = horaInicio.plus(Duration.ofMinutes(45));

    // Antes de ejecutar cada test, le presentamos estas condiciones iniciales.
    @BeforeEach
    void setUp() {

        organizador = new Empleado("000", "Simpson", "Homero", "hs@udec.cl");

        // Inicializamos una reunión virtual
        reunion = new ReunionVirtual(tipoReunion.TECNICA,
                new Date(), horaInicio, Duration.ofMinutes(60),
                organizador, "www.enlace.com");

    }

    @Test
    @DisplayName("Test para lanzar ReunionNoIniciadaException")
    void TestReunionNoIniciadaException(){

        assertThrows(ReunionNoIniciadaException.class, () -> {
            reunion.finalizar();
        }, "Se finaliza la reunion sin haberse iniciado, lo que lanza la exception");
    }


    @Test
    @DisplayName("Test para lanzar ReunionFinInvalidaException")
    void TestReunionFinINvalidaException(){

        reunion.iniciar();

        // La reunion iniciara 10 minutos en el futuro
        reunion.setHoraInicio(Instant.now().plus(Duration.ofMinutes(10)));

        // La finalizamos ahora, es decir 10 minutos antes
        assertThrows(HoraFinInvalidaException.class, () -> {
            reunion.finalizar();
        }, "Se espera la expetcion porque la reunion finaliza antes del inicio");
    }

}
