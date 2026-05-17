package Tarea2;

//representa alguien que no es de la empresa
public class InvitadoExterno extends Persona {
//construye un invitado con sus datos basicos

    public InvitadoExterno(String nombre, String apellidos, String correo) {
        super(nombre,apellidos,correo); //inicia los datos en la superclase
    }
    @Override
    public String toString() {
        return "Invitado externo: [Nombre = "+getNombre() +" "+getApellidos() +", Correo = "+ getCorreo()+"]";
    }
}