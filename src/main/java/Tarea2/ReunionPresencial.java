package Tarea2;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ReunionPresencial extends Reunion {
    private String sala;

    public ReunionPresencial(Date fecha, Instant horaPrevista, Duration duracionPrevista, Instant horaInicio, Instant horaFin){
        super(fecha, horaPrevista, duracionPrevista, horaInicio, horaFin);
        this.sala = sala;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
}
