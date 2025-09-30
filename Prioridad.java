package app.model;

/**
 * Representa la prioridad de una tarea.
 */
public enum Prioridad {
    HIGH("Alta"),
    MEDIUM("Media"),
    LOW("Baja");

    private final String descripcion;

    Prioridad(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Convierte un texto a Prioridad, útil al leer de BD o entrada de usuario.
     */
    public static Prioridad fromString(String text) {
        for (Prioridad p : Prioridad.values()) {
            if (p.name().equalsIgnoreCase(text) || p.getDescripcion().equalsIgnoreCase(text)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Prioridad no válida: " + text);
    }
}