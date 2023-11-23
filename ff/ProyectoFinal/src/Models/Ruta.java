package Models;

import java.io.Serializable;

public class Ruta implements Serializable {

    private static final long serialVersionUID = 1L;
    private String horaSalida;
    private String tiempoEspera;
    private String duracion;
    private Ciudad destino;
    private Ciudad origen;
    private TipoModalidad modalidad;

    public Ruta(String horaSalida, String tiempoEspera, String duracion) {
        this.horaSalida = horaSalida;
        this.tiempoEspera = tiempoEspera;
        this.duracion = duracion;
    }


    public Ruta(String horaSalida, String tiempoEspera, String duracion, Ciudad destino, Ciudad origen,
            TipoModalidad modalidad) {
        this.horaSalida = horaSalida;
        this.tiempoEspera = tiempoEspera;
        this.duracion = duracion;
        this.destino = destino;
        this.origen = origen;
        this.modalidad = modalidad;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(String tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public TipoModalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(TipoModalidad modalidad) {
        this.modalidad = modalidad;
    }

    @Override
    public String toString() {
        return "Ruta [horaSalida=" + horaSalida + ", tiempoEspera=" + tiempoEspera + ", duracion=" + duracion
                + ", destino=" + destino + ", origen=" + origen + ", modalidad=" + modalidad + "]";
    }
}
