package Tarea2;

/**
 * representa alguien que no es de la empresa, pero que se desea
 * invitar a la reunion.
 * Similar a Emplado pero este no cuenta con ID
 */
public class InvitadoExterno extends Persona implements Invitable {
//construye un invitado con sus datos basicos

    public InvitadoExterno(String nombre, String apellidos, String correo) {
        super(nombre,apellidos,correo); //inicia los datos en la superclase
    }

    @Override
    public void invitar() {
        System.out.println("Invitacion externa enviada a:" + getNombre() + " " + getApellidos() + " (" + getCorreo() + ")");
    }

    @Override
    public String toString() {
        return "Invitado externo: [Nombre = "+getNombre() +" "+getApellidos() +", Correo = "+ getCorreo()+"]";
    }
}