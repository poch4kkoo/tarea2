package Tarea2;

/**
 *
 */
public class Empleado implements Invitable {

    private String id;
    private String apellidos;
    private String nombre;
    private String correo;

    /**
     * Construye un empleado con su identificador y datos de contacto.
     * @param id es el identificador unico del empleado.
     * @param apellidos los apellidos completos del empleado.
     * @param nombre el nombre del empleado.
     * @param correo direccion de correo del empleado.
     */
    public Empleado(String id, String apellidos, String nombre, String correo) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.correo = correo;
    }

    //===========================================================================
    // Getters y Setters
    //===========================================================================
    public String getId() {
        return id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Envia una invitacion al empleado imprimiendo la confirmacion
     * con sus datos completos en la consola del sistema.
     */
    @Override
    public void invitar() {
        System.out.println("Invitacion enviada a: " +nombre+ " "+apellidos+" ("+correo+")");
    }

    @Override
    public String toString() {
        return "Informacion del empleado: [ID = " + id+ ", Nombre = " + nombre+ " " +apellidos+ ", Correo = " +correo +  "]";
    }
}
