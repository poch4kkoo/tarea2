package Tarea2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotaMasInformeTest {

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
    @DisplayName("Se genera informe con 2 notas. El informe solo se enfoca en crear las notas.")
    void testInformeTxtConNotas() {
        String Informe = "InformeNotasTest.txt";

        Empleado organizador = new Empleado("Test", "Test", "Test", "Test");

        Reunion reunion = new ReunionPresencial(tipoReunion.TECNICA,
                new Date(), Instant.now(), Duration.ofMinutes(60),
                organizador, "Sala 2");



        // Añadimos notas de prueba
        reunion.agregarNota(new Nota("Hola soy una nota"));
        reunion.agregarNota(new Nota("Nota numero 2"));

        reunion.iniciar();
        reunion.finalizar();

        // Generamos el informe
        reunion.generarInformeTxt(Informe);

        File archivo = new File(Informe);

        // Verificamos si se creo el informe
        assertTrue(archivo.exists(), "El informe debe haberse creado.");
        assertTrue(archivo.length() > 0, "no debe estar vacío.");
    }

}
