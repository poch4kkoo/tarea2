package Tarea2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class InvitadoExternoYAusenciasTest {

    private Reunion reunion;
    private Empleado organizador;
    private InvitadoExterno invitado1;
    private InvitadoExterno invitado2;
    private InvitadoExterno invitado3;
    private Instant horaInicio;

    @BeforeEach
    void setUp() {
        horaInicio = Instant.now();
        organizador = new Empleado("E1", "Simpson", "Homero", "hs@udec.cl");

        // Creamos dos invitados externos
        invitado1 = new InvitadoExterno("Alexis", "Sánchez", "as@gmail.com");
        invitado2 = new InvitadoExterno("Arturo", "Vidal", "av@gmail.com");
        invitado3 = new InvitadoExterno("Claudio", "Bravo", "cb@gmail.com");

        // Inicializamos una reunión Presencial
        reunion = new ReunionPresencial(tipoReunion.MARKETING,
                new Date(), horaInicio, Duration.ofMinutes(60),
                organizador, "Sala 3");

        // Registramos las invitaciones correspondientes en la reunión
        reunion.getInvitaciones().add(new Invitacion(invitado1, horaInicio));
        reunion.getInvitaciones().add(new Invitacion(invitado2, horaInicio));
        reunion.getInvitaciones().add(new Invitacion(invitado3, horaInicio));
    }

    @Test
    @DisplayName("Test de ausencia de un invitados externo")
    void testAsistenciaYAusenciaExternos() {
        // Alexis asiste a la reunion
        reunion.getAsistencias().add(new Asistencia(invitado1));
        reunion.getAsistencias().add(new Retraso(invitado3, horaInicio.plus(Duration.ofMinutes(15))));

        // Vidal no vino
        // Bravo llego tarde

        // Verificamos si se detecto la ausencia de vidal
        var ausencias = reunion.obtenerAusencias();
        assertEquals(1, ausencias.size(), "Debería registrarse 1 ausencia.");
        assertEquals(invitado2.getNombre(), ausencias.getFirst().getPersona().getNombre(),
                "El invitado ausente debería ser vidal.");
    }
}