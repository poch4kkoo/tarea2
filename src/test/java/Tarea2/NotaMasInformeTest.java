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
    @DisplayName("Se genera informe con 2 notas. El informe solo se enfoca en crear las notas.")
    void testInformeTxtConNotas() {
        String Informe = "InformeSoloNotas.txt";

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
