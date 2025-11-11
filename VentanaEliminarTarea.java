import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaEliminarTarea extends JFrame {
    public VentanaEliminarTarea(Usuario usuario) {
        setTitle("Eliminar Tarea");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        List<Tarea> tareas = usuario.getTareas();

        if (tareas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay tareas para eliminar.");
            dispose();
            return;
        }

        String[] opciones = tareas.stream()
            .map(Tarea::toString)
            .toArray(String[]::new);

        JComboBox<String> listaTareas = new JComboBox<>(opciones);
        JButton eliminar = new JButton("Eliminar");

        eliminar.addActionListener(e -> {
            int seleccion = listaTareas.getSelectedIndex();
            if (seleccion >= 0) {
                usuario.eliminarTarea(seleccion);
                JOptionPane.showMessageDialog(this, "Tarea eliminada.");
                dispose();
            }
        });

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Seleccione una tarea:"));
        panel.add(listaTareas);
        panel.add(eliminar);

        add(panel, BorderLayout.CENTER);
    }
}