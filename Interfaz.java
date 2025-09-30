package app.ui;

import app.model.Estado;
import app.model.Prioridad;
import app.model.Tarea;
import app.service.Servicios;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.screen.Screen;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Interfaz {
    private final Servicios service;
    private final MultiWindowTextGUI gui;

    public Interfaz(Servicios service, Screen screen) {
        this.service = service;
        this.gui = new MultiWindowTextGUI(screen);
    }

    public void showMainMenu() {
        BasicWindow window = new BasicWindow("Gestor de Tareas");
        Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));

        panel.addComponent(new Button("Crear tarea", this::showCreate));
        panel.addComponent(new Button("Listar tareas", this::showList));
        panel.addComponent(new Button("Filtrar tareas", this::showFilter));
        panel.addComponent(new Button("Marcar como completada", this::markCompleted));
        panel.addComponent(new Button("Eliminar completadas/caducadas", this::deleteOld));
        panel.addComponent(new Button("Salir", window::close));

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    private void showCreate() {
        BasicWindow w = new BasicWindow("Nueva tarea");
        Panel p = new Panel(new GridLayout(2));

        TextBox title = new TextBox();
        ComboBox<String> priority = new ComboBox<>("HIGH", "MEDIUM", "LOW");
        TextBox tags = new TextBox();

        p.addComponent(new Label("Título:")); p.addComponent(title);
        p.addComponent(new Label("Prioridad:")); p.addComponent(priority);
        p.addComponent(new Label("Etiquetas:")); p.addComponent(tags);

        p.addComponent(new Button("Guardar", () -> {
            try {
                service.crearTarea(
                        title.getText(),
                        Prioridad.valueOf(priority.getSelectedItem()),
                        tags.getText(),
                        LocalDateTime.now().plusDays(1)
                );
                MessageDialog.showMessageDialog(gui, "OK", "Tarea creada");
                w.close();
            } catch (Exception e) {
                MessageDialog.showMessageDialog(gui, "Error", e.getMessage());
            }
        }));
        p.addComponent(new Button("Cancelar", w::close));

        w.setComponent(p);
        gui.addWindowAndWait(w);
    }

    private void showList() {
        try {
            List<Tarea> tasks = service.listarTodas();
            StringBuilder sb = new StringBuilder();
            for (Tarea t : tasks) {
                sb.append(t.getId()).append(" - ")
                  .append(t.getTitle()).append(" [")
                  .append(t.getStatus()).append("] ")
                  .append(t.getPriority()).append("\n");
            }
            MessageDialog.showMessageDialog(gui, "Tareas", sb.toString());
        } catch (SQLException e) {
            MessageDialog.showMessageDialog(gui, "Error", e.getMessage());
        }
    }

    private void showFilter() {
        BasicWindow w = new BasicWindow("Filtrar");
        Panel p = new Panel(new GridLayout(2));

        ComboBox<String> status = new ComboBox<>("ANY", "PENDING", "COMPLETED", "OVERDUE");
        ComboBox<String> priority = new ComboBox<>("ANY", "HIGH", "MEDIUM", "LOW");

        p.addComponent(new Label("Estado:")); p.addComponent(status);
        p.addComponent(new Label("Prioridad:")); p.addComponent(priority);

        p.addComponent(new Button("Aplicar", () -> {
            try {
                Estado st = "ANY".equals(status.getSelectedItem()) ? null : Estado.valueOf(status.getSelectedItem());
                Prioridad pr = "ANY".equals(priority.getSelectedItem()) ? null : Prioridad.valueOf(priority.getSelectedItem());
                List<Tarea> result = service.filtrar(st, pr);
                StringBuilder sb = new StringBuilder();
                for (Tarea t : result) {
                    sb.append(t.getId()).append(" - ")
                      .append(t.getTitle()).append(" [")
                      .append(t.getStatus()).append("] ")
                      .append(t.getPriority()).append("\n");
                }
                MessageDialog.showMessageDialog(gui, "Filtrado", sb.toString());
                w.close();
            } catch (SQLException e) {
                MessageDialog.showMessageDialog(gui, "Error", e.getMessage());
            }
        }));
        p.addComponent(new Button("Cancelar", w::close));

        w.setComponent(p);
        gui.addWindowAndWait(w);
    }

    private void markCompleted() {
        String input = TextInputDialog.showDialog(gui, "Completar tarea", "ID de la tarea a marcar:", "");
        try {
            int id = Integer.parseInt(input);
            service.marcarComoCompletada(id);
            MessageDialog.showMessageDialog(gui, "OK", "Tarea marcada como completada");
        } catch (Exception e) {
            MessageDialog.showMessageDialog(gui, "Error", e.getMessage());
        }
    }

    private void deleteOld() {
        try {
            int count = service.eliminarCompletadasOCaducadas();
            MessageDialog.showMessageDialog(gui, "Eliminadas", count + " tareas eliminadas");
        } catch (SQLException e) {
            MessageDialog.showMessageDialog(gui, "Error", e.getMessage());
        }
    }
}