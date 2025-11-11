import java.time.LocalDate;

public class Tarea {
    public String titulo;
    public String estado;
    public String prioridad;
    public String categoria;
    public String horaProgramada;
    public LocalDate dia;

    public Tarea(String titulo, String estado, String prioridad, String categoria, String horaProgramada, LocalDate dia) {
        this.titulo = titulo;
        this.estado = estado;
        this.prioridad = prioridad;
        this.categoria = categoria;
        this.horaProgramada = horaProgramada;
        this.dia = dia;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getDia() {
        return dia;
    }

    public String toArchivo() {
        return titulo + ";" + estado + ";" + prioridad + ";" + categoria + ";" + horaProgramada + ";" + dia;
    }

    public static Tarea desdeArchivo(String linea) {
        String[] partes = linea.split(";");
        return new Tarea(
            partes[0],
            partes[1],
            partes[2],
            partes[3],
            partes[4],
            LocalDate.parse(partes[5])
        );
    }

    @Override
    public String toString() {
        return titulo + " [" + estado + "] - " + prioridad + " - " + categoria + " - " + horaProgramada + " - " + dia;
    }
}
