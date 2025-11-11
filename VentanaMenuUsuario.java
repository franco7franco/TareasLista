import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class VentanaMenuUsuario extends JFrame {
    public VentanaMenuUsuario(Usuario usuario) {
        setTitle("Menú de Usuario - " + usuario.id);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1, 10, 10));

        JButton verTareasBtn = new JButton("Ver tareas");
        JButton agregarBtn = new JButton("Agregar tarea");
        JButton filtrarBtn = new JButton("Filtrar por estado");
        JButton eliminarBtn = new JButton("Eliminar tareas finalizadas");
        JButton calendarioBtn = new JButton("Ver calendario");
        JButton notificacionesBtn = new JButton("Mis notificaciones");
        JButton cerrarSesionBtn = new JButton("Cerrar sesión");

        // ✅ Mostrar tareas en JOptionPane
        verTareasBtn.addActionListener(e -> {
            List<Tarea> tareas = usuario.getTareas();
            StringBuilder mensaje = new StringBuilder();

            if (tareas.isEmpty()) {
                mensaje.append("No hay tareas registradas.");
            } else {
                for (int i = 0; i < tareas.size(); i++) {
                    Tarea t = tareas.get(i);
                    mensaje.append((i + 1)).append(". ").append(t.titulo).append(" [").append(t.estado).append("]\n")
                           .append("   Prioridad: ").append(t.prioridad).append("\n")
                           .append("   Categoría: ").append(t.categoria).append("\n")
                           .append("   Hora: ").append(t.horaProgramada).append("\n")
                           .append("   Día: ").append(t.dia).append("\n\n");
                }
            }

            JOptionPane.showMessageDialog(this, mensaje.toString(), "Lista de Tareas", JOptionPane.INFORMATION_MESSAGE);
        });

        // ✅ Formulario visual para agregar tarea
        agregarBtn.addActionListener(e -> {
            JFrame formulario = new JFrame("Agregar nueva tarea");
            formulario.setSize(350, 400);
            formulario.setLocationRelativeTo(this);
            formulario.setLayout(new GridLayout(0, 1, 10, 10));
            formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JTextField tituloField = new JTextField();
            JComboBox<String> estadoBox = new JComboBox<>(new String[]{"Pendiente", "Completada"});
            JComboBox<String> prioridadBox = new JComboBox<>(new String[]{"Baja", "Media", "Alta"});
            JTextField categoriaField = new JTextField();
            JTextField horaField = new JTextField();
            JTextField fechaField = new JTextField();

            JButton guardarBtn = new JButton("Guardar");
            JButton cancelarBtn = new JButton("Cancelar");

            formulario.add(new JLabel("Título:"));
            formulario.add(tituloField);
            formulario.add(new JLabel("Estado:"));
            formulario.add(estadoBox);
            formulario.add(new JLabel("Prioridad:"));
            formulario.add(prioridadBox);
            formulario.add(new JLabel("Categoría:"));
            formulario.add(categoriaField);
            formulario.add(new JLabel("Hora (HH:mm):"));
            formulario.add(horaField);
            formulario.add(new JLabel("Fecha (yyyy-MM-dd):"));
            formulario.add(fechaField);
            formulario.add(guardarBtn);
            formulario.add(cancelarBtn);

            guardarBtn.addActionListener(ev -> {
                String titulo = tituloField.getText().trim();
                String estado = (String) estadoBox.getSelectedItem();
                String prioridad = (String) prioridadBox.getSelectedItem();
                String categoria = categoriaField.getText().trim();
                String hora = horaField.getText().trim();
                String fechaStr = fechaField.getText().trim();

                if (titulo.isEmpty() || categoria.isEmpty() || hora.isEmpty() || fechaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(formulario, "Por favor, completa todos los campos.");
                    return;
                }

                try {
                    LocalDate fecha = LocalDate.parse(fechaStr);
                    Tarea tarea = new Tarea(titulo, estado, prioridad, categoria, hora, fecha);
                    usuario.agregarTarea(tarea);
                    JOptionPane.showMessageDialog(formulario, "Tarea agregada correctamente.");
                    formulario.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formulario, "Fecha inválida. Usa el formato yyyy-MM-dd.");
                }
            });

            cancelarBtn.addActionListener(ev -> formulario.dispose());

            formulario.setVisible(true);
        });

        filtrarBtn.addActionListener(e -> {
            String estado = JOptionPane.showInputDialog("Estado a filtrar:");
            if (estado != null) usuario.filtrarPorEstado(estado);
        });

        eliminarBtn.addActionListener(e -> {
            usuario.eliminarFinalizadas();
            JOptionPane.showMessageDialog(this, "Tareas eliminadas.");
        });

        calendarioBtn.addActionListener(e -> {
            JFrame calendario = new VentanaCalendarioMensual(usuario);
            calendario.setVisible(true);
            setVisible(false);

            calendario.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    setVisible(true);
                }

                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    calendario.dispose();
                    setVisible(true);
                }
            });
        });

        notificacionesBtn.addActionListener(e -> VentanaNotificaciones.mostrar(usuario));

        cerrarSesionBtn.addActionListener(e -> {
            dispose();
            new VentanaPrincipal().setVisible(true);
        });

        add(verTareasBtn);
        add(agregarBtn);
        add(filtrarBtn);
        add(eliminarBtn);
        add(calendarioBtn);
        add(notificacionesBtn);
        add(cerrarSesionBtn);
    }
}