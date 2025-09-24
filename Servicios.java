package service;

import dao.TaskDAO;
import model.Task;
import java.time.LocalDateTime;
import java.util.List;

public class Servicios {
    private TareaDB dao = new TareaDB();

    public Servicios() {
        dao.createTable();
    }

    public void crearTarea(String titulo, String estado, LocalDateTime fechaHora, String prioridad,
                           String categoria, String recordatorio, String zonaHoraria,
                           String preferenciasNotificacion, String temaVista) {
        Tarea t = new Tarea(titulo, estado, fechaHora, prioridad, categoria, recordatorio, zonaHoraria, preferenciasNotificacion, temaVista);
        dao.insert(t);
    }

    public List<Tarea> filtrarPorEstado(String estado) {
        return dao.getByEstado(estado);
    }

    public void eliminarPorEstado(String estado) {
        dao.deleteByEstado(estado);
    }
}


