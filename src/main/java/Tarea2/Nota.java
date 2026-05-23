package Tarea2;

/**
 * Permite dejar una o varias notas para la reunion.
 * Estas se pueden observar al final de un informe.
 */
public class Nota {
    private String contenido;

    /**
     * Constructor en el cual se genera la nota
     *
     * @param contenido Un String con el contenido de la nota
     */
    public Nota(String contenido) {
        this.contenido = contenido;
    }

    //Getters, setters y toString

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return contenido;
    }
}
