package Tarea2;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ReunionVirtual extends Reunion {
    private String enlace;

    //se modofico agregando string enlace al constructor para que guarde el dato real
    public ReunionVirtual(tipoReunion tipo, Date fecha, Instant horaPrevista, Duration duracionPrevista, Empleado organizador, String enlace){
        super(tipo,fecha, horaPrevista, duracionPrevista, organizador);
        this.enlace=enlace;
    }
    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    @Override
    public String obtenerTipoOEnlace() {
        return "Virtual - Enlace: " + (enlace != null ? enlace : "No asignado");
    }
}
