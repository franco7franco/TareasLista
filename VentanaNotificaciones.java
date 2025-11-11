import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class VentanaNotificaciones {
    public static void mostrar(Usuario usuario) {
        StringBuilder pendientes = new StringBuilder();
        StringBuilder completadas = new StringBuilder();

        List<Tarea> tareas = usuario.getTareas();

        for (Tarea t : tareas) {
            String info = "• " + t.titulo + " (" + t.dia + " a las " + t.horaProgramada + ")\n";
            if (t.getEstado().equalsIgnoreCase("pendiente")) {
                pendientes.append(info);
            } else if (t.getEstado().equalsIgnoreCase("completada")) {
                completadas.append(info);
            }
        }

        StringBuilder mensaje = new StringBuilder("🔔 Mis Notificaciones\n\n");

        mensaje.append("📌 Pendientes:\n");
        mensaje.append(pendientes.length() > 0 ? pendientes : "   (Sin tareas pendientes)\n");

        mensaje.append("\n✅ Completadas:\n");
        mensaje.append(completadas.length() > 0 ? completadas : "   (Sin tareas completadas)\n");

        JOptionPane.showMessageDialog(null, mensaje.toString(), "Mis Notificaciones", JOptionPane.INFORMATION_MESSAGE);
    }
}