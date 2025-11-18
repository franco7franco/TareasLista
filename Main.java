
import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        new VentanaPrincipal().setVisible(true);
    }

    public static void agregarTarea(Usuario usuario) {
        String titulo = JOptionPane.showInputDialog("Título:");
        if (titulo == null || titulo.isEmpty()) return;
/// prueba de gestion de tareas
        String[] estados = {"Pendiente", "Completada"};
        String estado = (String) JOptionPane.showInputDialog(
                null, "Estado:", "Estado",
                JOptionPane.QUESTION_MESSAGE, null, estados, estados[0]);

        String[] prioridades = {"Baja", "Media", "Alta"};
        String prioridad = (String) JOptionPane.showInputDialog(
                null, "Prioridad:", "Prioridad",
                JOptionPane.QUESTION_MESSAGE, null, prioridades, prioridades[1]);

        String categoria = JOptionPane.showInputDialog("Categoría:");
        String hora = JOptionPane.showInputDialog("Hora programada (HH:mm):");
        String diaStr = JOptionPane.showInputDialog("Día (yyyy-MM-dd):");

        try {
            LocalDate dia = LocalDate.parse(diaStr);
            Tarea tarea = new Tarea(titulo, estado, prioridad, categoria, hora, dia);
            usuario.agregarTarea(tarea);
            JOptionPane.showMessageDialog(null, "Tarea agregada.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Usa yyyy-MM-dd.");
        }
    }
}
