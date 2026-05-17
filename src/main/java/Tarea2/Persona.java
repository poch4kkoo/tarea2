package Tarea2;
//clase que representa cualquier individuo
public abstract class Persona {
    private String nombre;
    private String apellidos;
    private String correo;
//constructuro para inicializar los datos basicos
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
}