package Tarea2;

/**
 * clase abstracta que representa a una persona.
 * agrupa la informacion necesaria para distingir
 * diferentes personas.
 */

public abstract class Persona {
    private String nombre;
    private String apellidos;
    private String correo;
//constructuro para inicializar los datos basicos

    /**
     * Constructor base para inicializar los datos esenciales de cualquier persona.
     *
     * @param nombre    El nombre del individuo.
     * @param apellidos Apellidos del individuo.
     * @param correo    La dirección de correo electrónico de la persona.
     */
    public Persona(String nombre,String apellidos,String correo) {
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.correo=correo;
    }
    //geters y seters comunes
    public String getNombre() {return nombre; }
    public void setNombre(String nombre) {this.nombre=nombre; }

    public String getApellidos() {return apellidos; }
    public void setApellidos(String apellidos) {this.apellidos=apellidos; }

    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo=correo; }

    @Override
    public String toString() {
        return "Persona [Nombre: " + nombre + ", Apellido: " + apellidos + ", Correo: " + correo + "]";
    }
}