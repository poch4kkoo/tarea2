package Tarea2;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ReunionPresencial extends Reunion {
    private String sala;

    public ReunionPresencial(tipoReunion tipo,Date fecha, Instant horaPrevista, Duration duracionPrevista, Empleado organizador, String sala){
        super(tipo,fecha, horaPrevista, duracionPrevista, organizador);
        this.sala = sala;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    @Override
    public String obtenerTipoOEnlace() {
        return "Presencial - Sala: " +(sala!=null ? sala :"No asignada");
    }
}
