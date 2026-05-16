package Tarea2;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ReunionVirtual extends Reunion {
    private String enlace;

    public ReunionVirtual(Date fecha, Instant horaPrevista, Duration duracionPrevista, Instant horaInicio, Instant horaFin){
        super(fecha, horaPrevista, duracionPrevista, horaInicio, horaFin);
        this.enlace = enlace;

    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
}
