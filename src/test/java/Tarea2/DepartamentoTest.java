package Tarea2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DepartamentoTest {

    @Test
    @DisplayName("Test de metodo para obtener cantidad de empleados")

    public void testObtenerCantidadEmpleados() {

        Departamento depto = new Departamento("deptoTest");
        Empleado emp1 = new Empleado("123", "Sinson", "Homero", "hs@udec.cl");
        Empleado emp2 = new Empleado("456", "Sinson", "Bart", "bs@udec.cl");

        depto.agregarEmpleado(emp1);
        depto.agregarEmpleado(emp2);

        int totalAgregados = depto.obtenerCantidadEmpleados();

        assertEquals(2, totalAgregados, "El total debería ser 2");
    }

    @Test
    @DisplayName("Test en caso de departamento vacío")

    public void testObtenerCantidadEmpleadosVacio(){
        // departamento nuevo sin asignarle empleados
        Departamento deptoVacio = new Departamento("deptoVacio");

        int totalInicial = deptoVacio.obtenerCantidadEmpleados();

        // Debería retornar 0
        assertEquals(0, totalInicial, "Un departamento con 0 empleados");

    }

    @Test
    @DisplayName("Test de invitar Departamento completo a la Reunion")

    public void ReunionDepartamentoTest() {

        // Se crea el departamento
        Departamento depto = new Departamento("deptoTest");
        Empleado emp1 = new Empleado("123", "Sinson", "Homero", "hs@udec.cl");
        Empleado emp2 = new Empleado("456", "Sinson", "Bart", "bs@udec.cl");

        depto.agregarEmpleado(emp1);
        depto.agregarEmpleado(emp2);


        // Se crea la reunion
        Empleado organizador = new Empleado("Test", "Test", "Test", "Test");
        Reunion reunion = new ReunionPresencial(tipoReunion.DIRECTIVA,
                new Date(), Instant.now(), Duration.ofMinutes(60),
                organizador, "Sala 2");


        // Se invita al departamento
        depto.invitar();

        //Se registra la asistencia
        for (Empleado emp : depto.getEmpleados()) {
            reunion.getInvitaciones().add(new Invitacion(emp, Instant.now()));
            reunion.getAsistencias().add(new Asistencia(emp));
        }

        // Validaciones
        assertEquals(2, reunion.obtenerTotalAsistencia(), "El total de asistentes debe ser 2");
        assertEquals(100.0f, reunion.obtenerPorcentajeAsistencia(), "La asistencia debería ser del 100%");
    }


}
