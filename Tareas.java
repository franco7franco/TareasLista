package model;

import java.time.LocalDateTime;

public class Tareas {
    private int id, titulo, estado; 
    private LocalDateTime fechaHora;
    private String prioridad,categoria, recordatorio, zonaHoraria, preferencia, temaVista;

    public Tareas() {}

    public Tareas(String titulo, String estado, LocalDateTime fechaHora, String prioridad,
                String categoria, String recordatorio, String zonaHoraria,
                String preferencia, String temaVista) {
        this.titulo = titulo;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.prioridad = prioridad;
        this.categoria = categoria;
        this.recordatorio = recordatorio;
        this.zonaHoraria = zonaHoraria;
        this.preferencia = preferencia;
        this.temaVista = temaVista;
    }
}



