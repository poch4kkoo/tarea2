package Tarea2;

import java.util.ArrayList;
import java.util.List;

public class Departamento implements Invitable {
    private String nombre;
    private List<Empleado> empleados;


    public Departamento(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }

    public int obtenerCantidadEmpleados(){
        return this.empleados.size();
    }

    @Override
    public void invitar () {
        System.out.println("Invitar departamento completo: " + this.nombre);
        for (int i = 0; i < empleados.size(); i++) {
            Empleado emp = empleados.get(i);
            emp.invitar();
        }
    }

    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }
}