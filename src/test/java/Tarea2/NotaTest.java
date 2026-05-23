package Tarea2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotaTest {

    @Test
    @DisplayName("Funcionamiento lista de notas")

    void testAgregarNotasExitosamente() {

        Empleado organizador = new Empleado("Test", "Test", "Test", "Test");

        Reunion reunion = new ReunionPresencial(tipoReunion.TECNICA,
                new Date(), Instant.now(), Duration.ofMinutes(60),
                organizador, "Sala 2");


        // Creamos las notas
        Nota nota1 = new Nota("Hola 123.");
        Nota nota2 = new Nota("Esto es una nota");

        reunion.agregarNota(nota1);
        reunion.agregarNota(nota2);

        // Verificaciones
        assertEquals(2, reunion.getNotas().size(), "Deberían ser 2 notas.");
        assertEquals("Hola 123.", reunion.getNotas().getFirst().getContenido(),
                "La Primera nota debe ser Hola123.");
    }

    @Test
    @DisplayName("Caso Extremo: Lanzar excepción si se intenta agregar una nota a una reunión ya finalizada")
    void testExcepcionNotaEnReunionFinalizada() {

        Empleado organizador = new Empleado("Test", "Test", "Test", "Test");

        Reunion reunion = new ReunionPresencial(tipoReunion.TECNICA,
                new Date(), Instant.now(), Duration.ofMinutes(60),
                organizador, "Sala 2");


        reunion.iniciar();
        reunion.finalizar();

        Nota notaTardia = new Nota("Acuerdo de último minuto");


        assertThrows(ReunionFinalizadaException.class, () -> {
            reunion.agregarNota(notaTardia);
        }, "Debería rechazar la nota porque la reunión ya se cerró");
    }
    
}
