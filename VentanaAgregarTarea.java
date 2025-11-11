import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class VentanaAgregarTarea extends JFrame {
    public VentanaAgregarTarea(Usuario usuario) {
        setTitle("Agregar Tarea");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        JTextField titulo = new JTextField();

        // Opciones numeradas pero con valores limpios
        String[] opcionesEstado = {"Pendiente", "Completada"};
        JComboBox<String> estado = new JComboBox<>(opcionesEstado);
        estado.insertItemAt("1. Pendiente", 0);
        estado.insertItemAt("2. Completada", 1);
        estado.removeItemAt(2);
        estado.removeItemAt(2);

        String[] opcionesPrioridad = {"Baja", "Media", "Alta"};
        JComboBox<String> prioridad = new JComboBox<>(opcionesPrioridad);
        prioridad.insertItemAt("1. Baja", 0);
        prioridad.insertItemAt("2. Media", 1);
        prioridad.insertItemAt("3. Alta", 2);
        prioridad.removeItemAt(3);
        prioridad.removeItemAt(3);
        prioridad.removeItemAt(3);

        JTextField categoria = new JTextField();
        JTextField hora = new JTextField();
        JTextField dia = new JTextField(); // formato yyyy-MM-dd

        JButton guardar = new JButton("Guardar");

        guardar.addActionListener(e -> {
            String t = titulo.getText();
            String est = estado.getSelectedItem().toString().replaceAll("^\\d\\.\\s*", "");
            String pri = prioridad.getSelectedItem().toString().replaceAll("^\\d\\.\\s*", "");
            String cat = categoria.getText();
            String h = hora.getText();
            String d = dia.getText();

            if (t.isEmpty() || est.isEmpty() || pri.isEmpty() || cat.isEmpty() || h.isEmpty() || d.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            try {
                LocalDate fecha = LocalDate.parse(d); // formato yyyy-MM-dd
                Tarea nueva = new Tarea(t, est, pri, cat, h, fecha);
                usuario.agregarTarea(nueva);
                JOptionPane.showMessageDialog(this, "Tarea agregada.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Usa yyyy-MM-dd.");
            }
        });

        add(new JLabel("Tarea:"));
        add(titulo);
        add(new JLabel("Estado:"));
        add(estado);
        add(new JLabel("Prioridad:"));
        add(prioridad);
        add(new JLabel("Categoría:"));
        add(categoria);
        add(new JLabel("Hora programada:"));
        add(hora);
        add(new JLabel("Día (yyyy-MM-dd):"));
        add(dia);
        add(new JLabel(""));
        add(guardar);
    }
}