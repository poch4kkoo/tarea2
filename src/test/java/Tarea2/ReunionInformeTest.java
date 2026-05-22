package Tarea2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class ReunionInformeTest {

    @Test
    @DisplayName("reunión y generacion de informe")
    void ReunionYGeneracionInforme() {

        Instant horaInicio = Instant.now();
        Instant horaFin = horaInicio.plus(Duration.ofMinutes(45));

        String nombreInforme = "ReunionInformeTest.txt";

        // Creamos la reunión (duración de 60 min)
        Reunion reunion = new ReunionVirtual(tipoReunion.TECNICA,
                          new Date(), horaInicio, Duration.ofMinutes(60),
                          horaInicio, horaFin, "www.enlace.com");


        Empleado organizador = new Empleado("E1", "Simpson", "Homero", "hs@udec.cl");
        Empleado emp2 = new Empleado("E2", "Simpson", "Bart", "bs@udec.cl");
        Empleado emp3 = new Empleado("E3", "Simpson", "Lisa", "ls@udec.cl");

        reunion.getInvitaciones().add(new Invitacion(organizador, horaInicio));
        reunion.getInvitaciones().add(new Invitacion(emp2, horaInicio));
        reunion.getInvitaciones().add(new Invitacion(emp3, horaInicio));

        // Inicio de la reunión

        // Homero llega a la hora
        reunion.getAsistencias().add(new Asistencia(organizador));

        // Bart llega 15 minutos tarde
        Instant horaRetrasoBart = reunion.getHoraInicio().plus(Duration.ofMinutes(15));
        reunion.getAsistencias().add(new Retraso(emp2, horaRetrasoBart));

        // Lisa no va a la reunión


        // La reunion duro 45 minutos
        reunion.setHoraFin(reunion.getHoraInicio().plus(Duration.ofMinutes(45)));

        // Generacion de informe
        reunion.generarInformeTxt(nombreInforme);

        // Verificación de creacion de informe
        File archivoResultado = new File(nombreInforme);
        assertTrue(archivoResultado.exists(), "El archivo de informe .txt debió haberse creado.");
        // El archivo no debe estar vacío
        assertTrue(archivoResultado.length() > 0, "El archivo de informe debería contener texto.");

        // Asistencia y retrasos en la reunión
        assertEquals(2, reunion.obtenerTotalAsistencia(), "Deben haber 2 personas.");
        assertEquals(1, reunion.obtenerRetrasos().size(), "1 retraso (Bart).");

    }
}