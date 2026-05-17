package Tarea2;

/**
 * Representa a un empleado de una empresa que puede ser invitado a una reunion.
 */
public class Empleado extends Persona implements Invitable {

    private String id;
    //las variables apellidos,nombre y correo se borran de aqui porque ya las hereda de Persona

    /**
     * Construye un empleado con su identificador y datos de contacto.
     * @param id es el identificador unico del empleado.
     * @param apellidos los apellidos completos del empleado.
     * @param nombre el nombre del empleado.
     * @param correo direccion de correo del empleado.
     */
    public Empleado(String id, String apellidos, String nombre, String correo) {
        super(nombre, apellidos, correo);
        this.id = id;

    }

    //===========================================================================
    // Getters y Setters
    //===========================================================================
    public String getId() {
        return id;
    }

    public String getApellidos() {
        return super.getApellidos();
    }

    public String getNombre() {
        return super.getNombre();
    }

    public String getCorreo() {
        return super.getCorreo();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setApellidos(String apellidos) {
        super.setApellidos(apellidos);
    }
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    public void setCorreo(String correo) {
        super.setCorreo(correo);
    }

    /**
     * Envia una invitacion al empleado imprimiendo la confirmacion
     * con los datos del empleado.
     */
    @Override
    public void invitar() {
        System.out.println("Invitacion enviada a: "+getNombre()+" " +getApellidos()+" (" +getCorreo() +")");;
    }

    /**
     * Genera una descripcion completa de los datos del empleado.
     * @return Cadena de texto con la informacion detallada del empleado.
     */
    @Override
    public String toString() {
        return "Informacion del empleado: [ID = "+id + ", Nombre = "+getNombre()+" "+getApellidos()+", Correo = "+getCorreo()+"]";
    }
}
