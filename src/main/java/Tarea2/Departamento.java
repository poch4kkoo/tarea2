package Tarea2;

import java.util.ArrayList;
import java.util.List;


/**
 * Representa un Departamento el cual puede contener grandes numeros
 * de empleados, lo que permite separrlos en grupos especificos
 * y gestionar muchas invitaciones rapidamente.
 */
public class Departamento implements Invitable {
    private String nombre;
    private List<Empleado> empleados;


    /**
     * Construye un Departamento y la lista de empleados
     * @param nombre El nombre del departamento.
     */
    public Departamento(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    /**
     * Añade un nuevo empleado a este departamento.
     *
     * @param empleado El Empleado que se integrará al departamento.
     */
    public void agregarEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }

    /**
     * Calcula la cantidad de empleados del departamento
     *
     * @return Cantidad de empleados como entero
     */
    public int obtenerCantidadEmpleados(){
        return this.empleados.size();
    }


    /**
     * Se encarga de invitar a todo el departamento uno por uno
     */
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

    @Override
    public String toString() {
        return "Departamento [nombre: " + nombre + ", Empleados: " + empleados + "]";
    }
}