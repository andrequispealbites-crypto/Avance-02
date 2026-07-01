package models;

import java.time.LocalDateTime;

public class Reporte {

    private String titulo;
    private String contenido;
    private LocalDateTime fechaGeneracion;

    public Reporte(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaGeneracion = LocalDateTime.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    @Override
    public String toString() {
        return titulo + "\nFecha: " + fechaGeneracion + "\n" + contenido;
    }
}
