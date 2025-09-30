package app.model;

/**
 * Representa el estado de una tarea en el sistema.
 */
public enum Estado {
    /**
     * La tarea está creada pero aún no se ha completado.
     */
    PENDING("Pendiente"),

    /**
     * La tarea fue completada correctamente.
     */
    COMPLETED("Completada"),

    /**
     * La tarea no se completó a tiempo y está vencida.
     */
    OVERDUE("Retrasada");

    private final String descripcion;

    Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Convierte un texto a Estado, útil al leer de BD o entrada de usuario.
     */
    public static Estado fromString(String text) {
        for (Estado s : Estado.values()) {
            if (s.name().equalsIgnoreCase(text) || s.getDescripcion().equalsIgnoreCase(text)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + text);
    }
}