import Tarea2.Departamento;
import Tarea2.Empleado;
import org.junit.jupiter.api.Test;


public class DepartamentoTest {

    @Test
    public void testObtenerCantidadEmpleados(){

        Departamento depto = new Departamento("deptoTest");
        Empleado emp1 = new Empleado("123", "Sinson", "Homero", "hs@udec.cl");
        Empleado emp2 = new Empleado("456", "Sinson", "Marge", "ms@udec.cl");

        depto.agregarEmpleado(emp1);
        depto.agregarEmpleado(emp2);

        int totalAgregados = depto.obtenerCantidadEmpleados();


    }
}
