package Tarea2;

public class Nota {
    private String contenido;

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
