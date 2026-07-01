package models;

public class ConfiguracionSistema {

    private String nombreSistema;
    private int tiempoAtencionMilisegundos;
    private boolean prioridadActiva;

    public ConfiguracionSistema() {
        this.nombreSistema = "Sistema de Turnos";
        this.tiempoAtencionMilisegundos = 1500;
        this.prioridadActiva = true;
    }

    public String getNombreSistema() {
        return nombreSistema;
    }

    public int getTiempoAtencionMilisegundos() {
        return tiempoAtencionMilisegundos;
    }

    public boolean isPrioridadActiva() {
        return prioridadActiva;
    }
}
