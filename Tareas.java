package model;

import java.time.LocalDateTime;

public class Tarea {
    private int id;
    private String titulo;
    private String estado; // pendiente, completada, retrasada
    private LocalDateTime fechaHora;
    private String prioridad; // alta, media, baja
    private String categoria;
    private String recordatorio; // texto o formato de notificación
    private String zonaHoraria;
    private String preferenciasNotificacion; // email, push, sonido
    private String temaVista; // filtros o vistas guardadas

    // Constructor vacío
    public Tarea() {}

    // Constructor completo
    public Tarea(String titulo, String estado, LocalDateTime fechaHora, String prioridad,
                String categoria, String recordatorio, String zonaHoraria,
                String preferenciasNotificacion, String temaVista) {
        this.titulo = titulo;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.prioridad = prioridad;
        this.categoria = categoria;
        this.recordatorio = recordatorio;
        this.zonaHoraria = zonaHoraria;
        this.preferenciasNotificacion = preferenciasNotificacion;
        this.temaVista = temaVista;
    }

    // Getters y setters...
}


